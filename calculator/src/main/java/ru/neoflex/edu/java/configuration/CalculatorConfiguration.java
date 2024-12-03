package ru.neoflex.edu.java.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.math.BigDecimal;

@ConfigurationProperties(prefix = "calculator")
public record CalculatorConfiguration(
        BigDecimal baseRate,
        BigDecimal insurancePayment
) {
}
