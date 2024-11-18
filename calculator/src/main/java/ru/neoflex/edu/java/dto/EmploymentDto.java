package ru.neoflex.edu.java.dto;

import ru.neoflex.edu.java.dto.enums.EmploymentStatus;
import ru.neoflex.edu.java.dto.enums.Position;

import java.math.BigDecimal;

public record EmploymentDto(
        EmploymentStatus employmentStatus,
        String employerINN,
        BigDecimal salary,
        Position position,
        Integer workExperienceTotal,
        Integer workExperienceCurrent
) {
}
