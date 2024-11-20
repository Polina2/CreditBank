package ru.neoflex.edu.java.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
@ConfigurationProperties(prefix = "app")
public record AppConfiguration(BigDecimal baseRate) {
}
