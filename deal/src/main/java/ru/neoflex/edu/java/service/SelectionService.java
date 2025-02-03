package ru.neoflex.edu.java.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.neoflex.edu.java.dto.EmailMessage;
import ru.neoflex.edu.java.dto.LoanOfferDto;
import ru.neoflex.edu.java.dto.enums.Theme;
import ru.neoflex.edu.java.entity.Statement;
import ru.neoflex.edu.java.entity.enums.ApplicationStatus;
import ru.neoflex.edu.java.kafka.DealProducer;
import ru.neoflex.edu.java.mapper.LoanOfferMapper;
import ru.neoflex.edu.java.repository.JpaStatementRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class SelectionService {
    private final JpaStatementRepository statementRepository;
    private final LoanOfferMapper mapper;
    private final DealProducer dealProducer;

    @Value("${deal.kafka.topics.finish-registration}")
    private String finishRegistrationTopic;
    @Value("${deal.mail.text.finish-registration}")
    private String text;

    public void select(LoanOfferDto request) {
        Statement statement = statementRepository.findById(request.statementId()).orElseThrow();
        statement.setStatus(ApplicationStatus.APPROVED);
        statement.addStatusHistory();
        statement.setAppliedOffer(mapper.toLoanOffer(request));
        statementRepository.save(statement);
        log.info("Saved statement {}", statement);
    }

    public void sendEmailMessage(LoanOfferDto request) {
        Statement statement = statementRepository.findById(request.statementId()).orElseThrow();
        EmailMessage emailMessage = new EmailMessage(
                statement.getClient().getEmail(), Theme.FINISH_REGISTRATION, statement.getStatementId(), text
        );
        dealProducer.sendMessage(finishRegistrationTopic, emailMessage);
        log.info("Sent message {}", emailMessage);
    }
}
