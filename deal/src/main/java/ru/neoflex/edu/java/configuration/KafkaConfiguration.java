package ru.neoflex.edu.java.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
@RequiredArgsConstructor
public class KafkaConfiguration {
    private final ProducerFactory<Object, Object> producerFactory;

    @Bean
    public KafkaTemplate<Object, Object> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory);
    }
}
