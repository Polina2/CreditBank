package ru.neoflex.edu.java.entity;

import ru.neoflex.edu.java.entity.enums.CreditStatus;
import ru.neoflex.edu.java.entity.json.PaymentScheduleElement;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class Credit {
    private UUID creditId;
    private BigDecimal amount;
    private Integer term;
    private BigDecimal monthlyPayment;
    private BigDecimal rate;
    private BigDecimal psk;
    private List<PaymentScheduleElement> paymentSchedule;
    private Boolean insuranceEnabled;
    private Boolean salaryClient;
    private CreditStatus creditStatus;
}
