package ru.neoflex.edu.java.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.neoflex.edu.java.dto.EmploymentDto;
import ru.neoflex.edu.java.dto.ScoringDataDto;
import ru.neoflex.edu.java.dto.enums.EmploymentStatus;
import ru.neoflex.edu.java.dto.enums.Gender;
import ru.neoflex.edu.java.dto.enums.MaritalStatus;
import ru.neoflex.edu.java.dto.enums.Position;

import java.math.BigDecimal;
import java.time.LocalDate;

class ScoringServiceTest {
    private OfferService mockOfferService;
    private ScoringService scoringService;

    @BeforeEach
    void setUp() {
        mockOfferService = Mockito.mock(OfferService.class);
        scoringService = new ScoringService(mockOfferService);
    }

    @Test
    void checkSalary() {
        BigDecimal salary = BigDecimal.valueOf(100000);
        Integer dependentAmount = 1;
        BigDecimal amount = BigDecimal.valueOf(1000000);

        boolean actual = scoringService.checkSalary(salary, dependentAmount, amount);

        Assertions.assertTrue(actual);
    }

    @Test
    void countResultRate() {
        Mockito.when(mockOfferService.getResultRate(Mockito.anyBoolean(), Mockito.anyBoolean(), Mockito.any()))
                .thenReturn(BigDecimal.valueOf(21));
        EmploymentStatus employmentStatus = EmploymentStatus.SELF_EMPLOYED;
        Position position = Position.WORKER;
        MaritalStatus maritalStatus = MaritalStatus.MARRIED;
        Gender gender = Gender.MALE;
        LocalDate birthDate = LocalDate.of(1990, 1, 1);
        ScoringDataDto scoringDataDto = new ScoringDataDto(
                gender, birthDate, maritalStatus, new EmploymentDto(employmentStatus, position), false, false
        );

        BigDecimal expected = BigDecimal.valueOf(18.5);
        BigDecimal actual = scoringService.countResultRate(scoringDataDto);

        Assertions.assertEquals(expected, actual);
    }
}