package ru.neoflex.edu.java.entity.json;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.neoflex.edu.java.entity.enums.EmploymentPosition;
import ru.neoflex.edu.java.entity.enums.EmploymentStatus;

import java.math.BigDecimal;

@NoArgsConstructor
@Setter
@Getter
@ToString
public class Employment {
    private EmploymentStatus status;
    private String employerInn;
    private BigDecimal salary;
    private EmploymentPosition position;
    private Integer workExperienceTotal;
    private Integer workExperienceCurrent;
}
