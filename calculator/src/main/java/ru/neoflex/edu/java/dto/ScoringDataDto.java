package ru.neoflex.edu.java.dto;

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
        BigDecimal amount,
        @NotNull
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
        Gender gender,
        @NotNull
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        @ValidBirthDate(minAge = 20, maxAge = 65)
        LocalDate birthDate,
        @NotNull
        @Pattern(regexp = "\\d{4}", message = "Invalid passport series")
        String passportSeries,
        @NotNull
        @Pattern(regexp = "\\d{6}", message = "Invalid passport number")
        String passportNumber,
        @NotNull
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        LocalDate passportIssueDate,
        @NotNull
        String passportIssueBranch,
        @NotNull
        MaritalStatus maritalStatus,
        @NotNull
        Integer dependentAmount,
        @NotNull
        @Valid
        EmploymentDto employment,
        @NotNull
        String accountNumber,
        @NotNull
        Boolean isInsuranceEnabled,
        @NotNull
        Boolean isSalaryClient
) {
        public ScoringDataDto(Gender gender, LocalDate birthDate, MaritalStatus maritalStatus, EmploymentDto employment, Boolean isInsuranceEnabled, Boolean isSalaryClient) {
                this(null, null, null, null, null, gender, birthDate, null, null, null, null, maritalStatus, null, employment, null, isInsuranceEnabled, isSalaryClient);
        }
}
