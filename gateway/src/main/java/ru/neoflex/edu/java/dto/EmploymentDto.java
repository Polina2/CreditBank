package ru.neoflex.edu.java.dto;

import jakarta.validation.constraints.Min;
import ru.neoflex.edu.java.dto.enums.EmploymentPosition;
import ru.neoflex.edu.java.dto.enums.EmploymentStatus;

import java.math.BigDecimal;

public record EmploymentDto(
        EmploymentStatus employmentStatus,
        String employerINN,
        BigDecimal salary,
        EmploymentPosition position,
        @Min(value = 18, message = "Bad total work experience")
        Integer workExperienceTotal,
        @Min(value = 3, message = "Bad current work experience")
        Integer workExperienceCurrent
) {
}
