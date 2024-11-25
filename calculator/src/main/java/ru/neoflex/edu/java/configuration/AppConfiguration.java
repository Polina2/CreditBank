package ru.neoflex.edu.java.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.math.BigDecimal;

@ConfigurationProperties(prefix = "app")
public record AppConfiguration(
        BigDecimal baseRate,
        BigDecimal insurancePayment
) {
}
