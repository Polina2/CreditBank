package ru.neoflex.edu.java.dto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record LoanOfferDto(
        @NotNull
        UUID statementId,
        BigDecimal requestedAmount,
        BigDecimal totalAmount,
        Integer term,
        BigDecimal monthlyPayment,
        BigDecimal rate,
        Boolean isInsuranceEnabled,
        Boolean isSalaryClient
) {
    public Builder builder() {
        return new Builder(this);
    }

    public static class Builder {
        private UUID statementId;
        private BigDecimal requestedAmount;
        private BigDecimal totalAmount;
        private Integer term;
        private BigDecimal monthlyPayment;
        private BigDecimal rate;
        private Boolean isInsuranceEnabled;
        private Boolean isSalaryClient;

        public Builder(LoanOfferDto loanOfferDto) {
            this.statementId = loanOfferDto.statementId;
            this.requestedAmount = loanOfferDto.requestedAmount;
            this.totalAmount = loanOfferDto.totalAmount;
            this.term = loanOfferDto.term;
            this.monthlyPayment = loanOfferDto.monthlyPayment;
            this.rate = loanOfferDto.rate;
            this.isInsuranceEnabled = loanOfferDto.isInsuranceEnabled;
            this.isSalaryClient = loanOfferDto.isSalaryClient;
        }

        public Builder statementId(UUID statementId) {
            this.statementId = statementId;
            return this;
        }

        public LoanOfferDto build() {
            return new LoanOfferDto(
                    this.statementId,
                    this.requestedAmount,
                    this.totalAmount,
                    this.term,
                    this.monthlyPayment,
                    this.rate,
                    this.isInsuranceEnabled,
                    this.isSalaryClient
            );
        }
    }
}
