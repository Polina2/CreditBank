package ru.neoflex.edu.java.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class ClientConfiguration {
    @Bean
    public RestClient dealWebClient(@Value("${statement.dealBaseUrl}") String baseUrl) {
        return RestClient.builder().baseUrl(baseUrl).build();
    }
}
