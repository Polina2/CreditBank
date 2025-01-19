package ru.neoflex.edu.java.dto;

import ru.neoflex.edu.java.entity.enums.CreditStatus;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record CreditEntityDto(
        UUID creditId,
        BigDecimal amount,
        Integer term,
        BigDecimal monthlyPayment,
        BigDecimal rate,
        BigDecimal psk,
        Boolean isInsuranceEnabled,
        Boolean isSalaryClient,
        List<PaymentScheduleElementDto> paymentSchedule,
        CreditStatus creditStatus
) {
}
