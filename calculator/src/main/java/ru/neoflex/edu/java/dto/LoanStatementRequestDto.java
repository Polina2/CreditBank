package ru.neoflex.edu.java.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import ru.neoflex.edu.java.validation.ValidBirthDate;

import java.math.BigDecimal;
import java.time.LocalDate;

public record LoanStatementRequestDto(
        @NotNull
        @Min(value = 20000, message = "Invalid amount")
        BigDecimal amount,
        @NotNull
        @Min(value = 6, message = "Invalid term")
        Integer term,
        @NotNull
        @Size(min = 2, max = 30, message = "Invalid first name")
        String firstName,
        @NotNull
        @Size(min = 2, max = 30, message = "Invalid last name")
        String lastName,
        @Size(min = 2, max = 30, message = "Invalid middle name")
        String middleName,
        @NotNull
        @Pattern(regexp = "^[a-z0-9A-Z_!#$%&'*+/=?`{|}~^.-]+@[a-z0-9A-Z.-]+$", message = "Invalid email")
        String email,
        @NotNull
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        @ValidBirthDate(minAge = 18)
        LocalDate birthDate,
        @NotNull
        @Pattern(regexp = "\\d{4}", message = "Invalid passport series")
        String passportSeries,
        @NotNull
        @Pattern(regexp = "\\d{6}", message = "Invalid passport number")
        String passportNumber
) {
}
