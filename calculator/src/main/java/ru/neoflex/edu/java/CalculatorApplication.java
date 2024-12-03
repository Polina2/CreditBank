package ru.neoflex.edu.java;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.neoflex.edu.java.configuration.CalculatorConfiguration;

@SpringBootApplication
@EnableConfigurationProperties(CalculatorConfiguration.class)
public class CalculatorApplication {
    public static void main(String[] args) {
        SpringApplication.run(CalculatorApplication.class, args);
    }
}
