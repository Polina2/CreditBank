package ru.neoflex.edu.java.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.neoflex.edu.java.dto.EmailMessage;
import ru.neoflex.edu.java.dto.enums.Theme;
import ru.neoflex.edu.java.entity.Statement;
import ru.neoflex.edu.java.entity.enums.ApplicationStatus;
import ru.neoflex.edu.java.kafka.DealProducer;
import ru.neoflex.edu.java.repository.JpaStatementRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreditIssueService {
    private final JpaStatementRepository statementRepository;
    private final DealProducer dealProducer;

    @Value("${deal.mail.text.credit-issued}")
    private String text;
    @Value("${deal.mail.text.wrong-ses-code}")
    private String wrongSesText;
    @Value("${deal.kafka.topics.credit-issued}")
    private String creditIssuedTopic;
    @Value("${deal.kafka.topics.statement-denied}")
    private String statementDeniedTopic;

    public void signDocuments(String statementId, String sesCode) {
        Statement statement = statementRepository.findById(UUID.fromString(statementId)).orElseThrow();
        String trueSesCode = statement.getSesCode();
        if (trueSesCode.equals(sesCode)) {
            statement.setStatus(ApplicationStatus.DOCUMENT_SIGNED);
            statement.addStatusHistory();
            statement = statementRepository.save(statement);
            log.info("Saved statement {}", statement);

            EmailMessage emailMessage = new EmailMessage(
                    statement.getClient().getEmail(), Theme.CREDIT_ISSUED, statement.getStatementId(), text
            );
            dealProducer.sendMessage(creditIssuedTopic, emailMessage);
            log.info("Sent message {}", emailMessage);

            statement.setStatus(ApplicationStatus.CREDIT_ISSUED);
            statement.addStatusHistory();
            statementRepository.save(statement);
            log.info("Saved statement {}", statement);
        } else {
            EmailMessage emailMessage = new EmailMessage(
                    statement.getClient().getEmail(), Theme.WRONG_SES_CODE, statement.getStatementId(), wrongSesText
            );
            dealProducer.sendMessage(statementDeniedTopic, emailMessage);
            log.info("Sent message {}", emailMessage);
        }
    }
}
