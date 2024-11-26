package ru.neoflex.edu.java.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.neoflex.edu.java.dto.ScoringDataDto;
import ru.neoflex.edu.java.dto.enums.EmploymentStatus;
import ru.neoflex.edu.java.dto.enums.Gender;
import ru.neoflex.edu.java.dto.enums.MaritalStatus;
import ru.neoflex.edu.java.dto.enums.Position;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

@Service
@RequiredArgsConstructor
public class ScoringService {
    private static final BigDecimal MINIMUM_WAGE = BigDecimal.valueOf(13000);

    private final OfferService offerService;

    private int getAge(LocalDate birthDate) {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    public boolean checkSalary(BigDecimal salary, Integer dependentAmount, BigDecimal amount) {
        return
                salary
                .subtract(
                        MINIMUM_WAGE
                        .multiply(BigDecimal.valueOf(dependentAmount))
                )
                .multiply(BigDecimal.valueOf(24))
                .compareTo(amount) >= 0;
    }

    public BigDecimal countResultRate(ScoringDataDto scoringData) {
        BigDecimal resultRate =
                offerService.getResultRate(scoringData.isInsuranceEnabled(), scoringData.isSalaryClient());
        resultRate = resultRateByEmploymentStatus(resultRate, scoringData.employment().employmentStatus());
        resultRate = resultRateByPosition(resultRate, scoringData.employment().position());
        resultRate = resultRateByMaritalStatus(resultRate, scoringData.maritalStatus());
        resultRate = resultRateByGenderAndAge(resultRate, scoringData.gender(), getAge(scoringData.birthDate()));
        return resultRate;
    }

    private BigDecimal resultRateByEmploymentStatus(BigDecimal rate, EmploymentStatus employmentStatus) {
        switch (employmentStatus) {
            case SELF_EMPLOYED -> {
                return rate.add(BigDecimal.valueOf(1.5));
            }
            case BUSINESS_OWNER -> {
                return rate.add(BigDecimal.ONE);
            }
        }
        return rate;
    }

    private BigDecimal resultRateByPosition(BigDecimal rate, Position position) {
        switch (position) {
            case MID_MANAGER -> {
                return rate.subtract(BigDecimal.ONE);
            }
            case TOP_MANAGER -> {
                return rate.subtract(BigDecimal.TWO);
            }
        }
        return rate;
    }

    private BigDecimal resultRateByMaritalStatus(BigDecimal rate, MaritalStatus maritalStatus) {
        switch (maritalStatus) {
            case MARRIED -> {
                return rate.subtract(BigDecimal.TWO);
            }
            case DIVORCED -> {
                return rate.add(BigDecimal.ONE);
            }
            case WIDOW_WIDOWER -> {
                return rate.subtract(BigDecimal.ONE);
            }
        }
        return rate;
    }

    private BigDecimal resultRateByGenderAndAge(BigDecimal rate, Gender gender, Integer age) {
        switch (gender) {
            case FEMALE -> {
                if (age >= 32 && age <= 60)
                    return rate.subtract(BigDecimal.TWO);
            }
            case MALE -> {
                if (age >= 30 && age <= 55)
                    return rate.subtract(BigDecimal.TWO);
            }
            case NON_BINARY -> {
                return rate.add(BigDecimal.valueOf(5));
            }
        }
        return rate;
    }
}
