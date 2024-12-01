package ru.neoflex.edu.java.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.neoflex.edu.java.dto.PaymentScheduleElementDto;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CreditServiceTest {
    private CreditService creditService;
    private OfferService mockOfferService;
    private ScoringService mockScoringService;

    @BeforeEach
    void setUp() {
        mockOfferService = Mockito.mock(OfferService.class);
        mockScoringService = Mockito.mock(ScoringService.class);
        creditService = new CreditService(mockScoringService, mockOfferService);
    }

    @Test
    void getPaymentSchedule() {
        BigDecimal amount = BigDecimal.valueOf(20000);
        Integer term = 6;
        BigDecimal monthlyPayment = BigDecimal.valueOf(3450.97);
        BigDecimal rate = BigDecimal.valueOf(12);
        Mockito.when(mockOfferService.countMonthRate(Mockito.any())).thenReturn(BigDecimal.valueOf(0.01));

        List<BigDecimal> expectedRemainingDebts = List.of(
                BigDecimal.valueOf(20000),
                BigDecimal.valueOf(16749.03),
                BigDecimal.valueOf(13465.55),
                BigDecimal.valueOf(10149.24),
                BigDecimal.valueOf(6799.76),
                BigDecimal.valueOf(3416.79),
                BigDecimal.valueOf(0)
        );
        List<PaymentScheduleElementDto> actual = creditService.getPaymentSchedule(amount, term, monthlyPayment, rate);
        List<BigDecimal> actualRemainingDebts = actual.stream().map(PaymentScheduleElementDto::remainingDebt).toList();

        Assertions.assertEquals(expectedRemainingDebts, actualRemainingDebts);
    }

    @Test
    void getPsk() {
        BigDecimal amount = BigDecimal.valueOf(20000);
        BigDecimal rate = BigDecimal.valueOf(12);
        Integer term = 6;
        BigDecimal monthlyPayment = BigDecimal.valueOf(3450.97);
        Boolean isInsuranceEnabled = false;
        Mockito.when(mockOfferService.getTotalPayment(amount, rate, term, monthlyPayment, isInsuranceEnabled))
                .thenReturn(BigDecimal.valueOf(20705.8));

        BigDecimal expected = BigDecimal.valueOf(7.06);
        BigDecimal actual = creditService.getPsk(amount, rate, term, monthlyPayment, isInsuranceEnabled);

        Assertions.assertEquals(expected, actual);
    }
}