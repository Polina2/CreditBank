package ru.neoflex.edu.java.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.neoflex.edu.java.dto.EmailMessage;
import ru.neoflex.edu.java.dto.enums.Theme;
import ru.neoflex.edu.java.entity.Statement;
import ru.neoflex.edu.java.kafka.DealProducer;
import ru.neoflex.edu.java.repository.JpaStatementRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class SesCodeService {
    private final JpaStatementRepository statementRepository;
    private final DealProducer dealProducer;

    @Value("${deal.mail.text.ses-code}")
    private String text;
    @Value("${deal.kafka.topics.ses-code}")
    private String sesCodeTopic;

    public void sendSesCode(String statementId) {
        Statement statement = statementRepository.findById(UUID.fromString(statementId)).orElseThrow();
        String sesCode = UUID.randomUUID().toString();
        statement.setSesCode(sesCode);
        statementRepository.save(statement);
        log.info("Saved statement {}", statement);

        text = text.replace("{statementId}", statementId).replace("{code}", sesCode);
        EmailMessage emailMessage = new EmailMessage(
                statement.getClient().getEmail(), Theme.SES_CODE, statement.getStatementId(), text
        );
        dealProducer.sendMessage(sesCodeTopic, emailMessage);
        log.info("Sent message {}", emailMessage);
    }
}
