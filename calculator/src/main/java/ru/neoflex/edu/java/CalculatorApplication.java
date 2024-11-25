package ru.neoflex.edu.java;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.neoflex.edu.java.configuration.AppConfiguration;

@SpringBootApplication
@EnableConfigurationProperties(AppConfiguration.class)
public class CalculatorApplication {
    public static void main(String[] args) {
        SpringApplication.run(CalculatorApplication.class, args);
    }
}
