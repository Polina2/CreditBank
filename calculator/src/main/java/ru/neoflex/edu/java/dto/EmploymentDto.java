package ru.neoflex.edu.java.dto;

import jakarta.validation.constraints.Min;
import ru.neoflex.edu.java.dto.enums.EmploymentStatus;
import ru.neoflex.edu.java.dto.enums.Position;
import ru.neoflex.edu.java.validation.ValidEnum;

import java.math.BigDecimal;

public record EmploymentDto(
        @ValidEnum(regexp = "EMPLOYED|SELF_EMPLOYED|BUSINESS_OWNER")
        EmploymentStatus employmentStatus,
        String employerINN,
        BigDecimal salary,
        Position position,
        @Min(value = 18, message = "Bad total work experience")
        Integer workExperienceTotal,
        @Min(value = 3, message = "Bad current work experience")
        Integer workExperienceCurrent
) {
        public EmploymentDto(EmploymentStatus employmentStatus, Position position) {
                this(employmentStatus, null, null, position, null, null);
        }
}
