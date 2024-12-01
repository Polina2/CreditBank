package ru.neoflex.edu.java.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import ru.neoflex.edu.java.dto.enums.Gender;
import ru.neoflex.edu.java.dto.enums.MaritalStatus;
import ru.neoflex.edu.java.validation.ValidBirthDate;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ScoringDataDto(
        @NotNull
        @Schema(description = "Сумма займа")
        BigDecimal amount,
        @NotNull
        @Schema(description = "Срок кредита")
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
        @Schema(description = "Пол")
        Gender gender,
        @NotNull
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        @ValidBirthDate(minAge = 20, maxAge = 65)
        LocalDate birthDate,
        @NotNull
        @Schema(description = "Серия паспорта")
        @Pattern(regexp = "\\d{4}", message = "Invalid passport series")
        String passportSeries,
        @NotNull
        @Schema(description = "Номер паспорта")
        @Pattern(regexp = "\\d{6}", message = "Invalid passport number")
        String passportNumber,
        @NotNull
        @Schema(description = "Дата выдачи паспорта")
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        LocalDate passportIssueDate,
        @NotNull
        @Schema(description = "Кем выдан паспорт")
        String passportIssueBranch,
        @NotNull
        @Schema(description = "Семейное положение")
        MaritalStatus maritalStatus,
        @NotNull
        @Schema(description = "Количество иждивенцев")
        Integer dependentAmount,
        @NotNull
        @Valid
        @Schema(description = "Трудоустройство")
        EmploymentDto employment,
        @NotNull
        @Schema(description = "Номер счета")
        String accountNumber,
        @NotNull
        @Schema(description = "Наличие страховки")
        Boolean isInsuranceEnabled,
        @NotNull
        @Schema(description = "Зарплатный ли клиент")
        Boolean isSalaryClient
) {
        public ScoringDataDto(Gender gender, LocalDate birthDate, MaritalStatus maritalStatus, EmploymentDto employment, Boolean isInsuranceEnabled, Boolean isSalaryClient) {
                this(null, null, null, null, null, gender, birthDate, null, null, null, null, maritalStatus, null, employment, null, isInsuranceEnabled, isSalaryClient);
        }
}
