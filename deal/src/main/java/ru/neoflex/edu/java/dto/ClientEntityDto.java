package ru.neoflex.edu.java.dto;

import ru.neoflex.edu.java.entity.enums.Gender;
import ru.neoflex.edu.java.entity.enums.MaritalStatus;

import java.time.LocalDate;
import java.util.UUID;

public record ClientEntityDto(
        UUID clientId,
        String lastName,
        String firstName,
        String middleName,
        LocalDate birthDate,
        String email,
        Gender gender,
        MaritalStatus maritalStatus,
        Integer dependentAmount,
        PassportDto passport,
        EmploymentDto employment,
        String accountNumber
) {
}
