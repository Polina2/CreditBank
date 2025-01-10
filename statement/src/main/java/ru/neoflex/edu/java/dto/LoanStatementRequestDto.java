package ru.neoflex.edu.java.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

public record LoanStatementRequestDto(
        @NotNull
        @Schema(description = "Сумма займа")
        @Min(value = 20000, message = "Invalid amount")
        BigDecimal amount,
        @NotNull
        @Schema(description = "Срок кредита")
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
        LocalDate birthDate,
        @NotNull
        @Schema(description = "Серия паспорта")
        @Pattern(regexp = "\\d{4}", message = "Invalid passport series")
        String passportSeries,
        @NotNull
        @Schema(description = "Номер паспорта")
        @Pattern(regexp = "\\d{6}", message = "Invalid passport number")
        String passportNumber
) {
        public LoanStatementRequestDto() {
                this(null, null, null, null, null, null, null, null, null);
        }
}
