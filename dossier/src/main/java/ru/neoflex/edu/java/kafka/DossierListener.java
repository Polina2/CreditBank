package ru.neoflex.edu.java.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import ru.neoflex.edu.java.dto.EmailMessage;
import ru.neoflex.edu.java.service.EmailService;

@Component
@Slf4j
@RequiredArgsConstructor
public class DossierListener {
    private final EmailService emailService;

    @KafkaListener(
            topics = "${dossier.kafka.topics.fr}",
            groupId = "dossier",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void finishRegistration(@Payload EmailMessage email) {
        log.info("Got finish registration message {}", email);
        emailService.sendMessage(email.address(), email.theme().name(), email.text());
    }

    @KafkaListener(
            topics = "${dossier.kafka.topics.cd}",
            groupId = "dossier",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void createDocuments(@Payload EmailMessage email) {
        log.info("Got create documents message");
        emailService.sendMessage(email.address(), email.theme().name(), email.text());
    }

    @KafkaListener(
            topics = "${dossier.kafka.topics.sd}",
            groupId = "dossier",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void sendDocuments(@Payload EmailMessage email) {
        log.info("Got send documents message");
        emailService.sendMessage(email.address(), email.theme().name(), email.text());
    }

    @KafkaListener(
            topics = "${dossier.kafka.topics.ss}",
            groupId = "dossier",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void sesCode() {
        log.info("Got ses code message");
    }

    @KafkaListener(
            topics = "${dossier.kafka.topics.ci}",
            groupId = "dossier",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void creditIssued() {
        log.info("Got credit issued message");
    }

    @KafkaListener(
            topics = "${dossier.kafka.topics.std}",
            groupId = "dossier",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void statementDenied() {
        log.info("Got statement denied message");
    }
}
