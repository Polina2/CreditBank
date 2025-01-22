package ru.neoflex.edu.java.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DealProducer {
    private final KafkaTemplate<Object, Object> kafkaTemplate;

    public void sendMessage(String topicName, Object msg) {
        kafkaTemplate.send(topicName, msg);
    }
}
