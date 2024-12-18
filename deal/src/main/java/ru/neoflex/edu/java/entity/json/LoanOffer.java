package ru.neoflex.edu.java.entity.json;

import java.math.BigDecimal;
import java.util.UUID;

public class LoanOffer {
    private UUID statementId;
    private BigDecimal requestedAmount;
    private BigDecimal totalAmount;
    private Integer term;
    private BigDecimal monthlyPayment;
    private BigDecimal rate;
    private Boolean isInsuranceEnabled;
    private Boolean isSalaryClient;
}
