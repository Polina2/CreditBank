package ru.neoflex.edu.java.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DossierListener {

    @KafkaListener(
            topics = "${dossier.kafka.topics.fr}",
            groupId = "dossier",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void finishRegistration() {
        log.info("Got finish registration message");
        //TODO: send email
    }

    @KafkaListener(
            topics = "${dossier.kafka.topics.cd}",
            groupId = "dossier",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void createDocuments() {
        log.info("Got create documents message");
    }

    @KafkaListener(
            topics = "${dossier.kafka.topics.sd}",
            groupId = "dossier",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void sendDocuments() {
        log.info("Got send documents message");
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
