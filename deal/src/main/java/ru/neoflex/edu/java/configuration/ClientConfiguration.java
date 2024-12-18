package ru.neoflex.edu.java.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ClientConfiguration {
    public WebClient calculatorRestTemplate(@Value("${deal.calculatorBaseUrl}") String baseUrl) {
        return WebClient.builder().baseUrl(baseUrl).build();
    }
}
