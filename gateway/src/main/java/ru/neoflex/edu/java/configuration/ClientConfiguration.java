package ru.neoflex.edu.java.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class ClientConfiguration {
    @Bean
    public RestClient statementWebClient(@Value("${gateway.statementClient.baseUrl}") String baseUrl) {
        return RestClient.builder().baseUrl(baseUrl).build();
    }

    @Bean
    public RestClient dealWebClient(@Value("${gateway.dealClient.baseUrl}") String baseUrl) {
        return RestClient.builder().baseUrl(baseUrl).build();
    }
}
