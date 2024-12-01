package ru.neoflex.edu.java.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class OfferServiceTest {
    private final OfferService offerService = new OfferService();

    @Test
    void getTotalPayment() {
        BigDecimal amount = BigDecimal.valueOf(20000);
        BigDecimal rate = BigDecimal.valueOf(12);
        Integer term = 6;
        BigDecimal monthlyPayment = BigDecimal.valueOf(3450.97);
        Boolean isInsuranceEnabled = false;

        BigDecimal expected = BigDecimal.valueOf(20705.80).setScale(2);
        BigDecimal actual = offerService
                .getTotalPayment(amount, rate, term, monthlyPayment, isInsuranceEnabled);

        Assertions.assertEquals(expected, actual);
    }

    @ParameterizedTest
    @CsvSource({
            "false, false, 21",
            "false, true, 20",
            "true, false, 19.5",
            "true, true, 18.5"
    }
    )
    void getResultRate(boolean isInsuranceEnabled, boolean isSalaryClient, BigDecimal expected) {
        BigDecimal baseRate = BigDecimal.valueOf(21);

        BigDecimal actual = offerService.getResultRate(isInsuranceEnabled, isSalaryClient, baseRate);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void countMonthlyPayment() {
        BigDecimal amount = BigDecimal.valueOf(20000);
        BigDecimal rate = BigDecimal.valueOf(12);
        Integer term = 6;

        BigDecimal expected = BigDecimal.valueOf(3450.97);
        BigDecimal actual = offerService
                .countMonthlyPayment(amount, rate, term);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void countMonthRate() {
        BigDecimal rate = BigDecimal.valueOf(18.5);

        BigDecimal expected = BigDecimal.valueOf(0.02);
        BigDecimal actual = offerService.countMonthRate(rate);

        Assertions.assertEquals(expected, actual);
    }
}