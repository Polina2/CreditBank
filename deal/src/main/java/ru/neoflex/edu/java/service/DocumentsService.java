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
public class DocumentsService {
    private final JpaStatementRepository statementRepository;
    private final DealProducer dealProducer;

    @Value("${deal.mail.text.send-documents}")
    private String text;
    @Value("${deal.kafka.topics.send-documents}")
    private String sendDocumentsTopic;

    public void createDocuments(String statementId) {
        Statement statement = statementRepository.findById(UUID.fromString(statementId)).orElseThrow();
        statement.setStatus(ApplicationStatus.PREPARE_DOCUMENTS);
        statementRepository.save(statement);
        log.info("Saved statement {}", statement);

        text = text.replace("{statementId}", statementId);
        EmailMessage emailMessage = new EmailMessage(
                statement.getClient().getEmail(), Theme.DOCUMENTS_PREPARED, statement.getStatementId(), text
        );
        dealProducer.sendMessage(sendDocumentsTopic, emailMessage);
        log.info("Sent message {}", emailMessage);
    }
}
