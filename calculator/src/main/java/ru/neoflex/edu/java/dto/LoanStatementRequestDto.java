package ru.neoflex.edu.java.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record LoanStatementRequestDto(
        BigDecimal amount,
        Integer term,
        String firstName,
        String lastName,
        String middleName,
        String email,
        LocalDate birthDate,
        String passportSeries,
        String passportNumber
) {
}
