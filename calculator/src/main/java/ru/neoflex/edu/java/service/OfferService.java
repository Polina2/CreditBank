package ru.neoflex.edu.java.service;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.neoflex.edu.java.dto.LoanOfferDto;
import ru.neoflex.edu.java.dto.LoanStatementRequestDto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Getter
@Slf4j
public class OfferService {
    public static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP;
    @Value(value = "${app.baseRate:21}")
    private BigDecimal baseRate;
    @Value(value = "${app.insurancePayment:100000}")
    private BigDecimal insurancePayment;
    public List<LoanOfferDto> getOffers(LoanStatementRequestDto request) {
        log.atDebug().log("getOffers called at {}", LocalDateTime.now());
        UUID statementId = UUID.randomUUID();
        return List.of(
                getOffer(false, false, request, statementId),
                getOffer(false, true, request, statementId),
                getOffer(true, false, request, statementId),
                getOffer(true, true, request, statementId)
        );
    }

    private LoanOfferDto getOffer(
            boolean isInsuranceEnabled, boolean isSalaryClient, LoanStatementRequestDto request, UUID statementId
    ) {
        BigDecimal rate = getResultRate(isInsuranceEnabled, isSalaryClient, this.baseRate);
        BigDecimal monthlyPayment = countMonthlyPayment(request.amount(), rate, request.term());
        BigDecimal totalPayment =
                getTotalPayment(request.amount(), rate, request.term(), monthlyPayment, isInsuranceEnabled);
        return new LoanOfferDto(
                statementId,
                request.amount(),
                totalPayment,
                request.term(),
                monthlyPayment,
                rate,
                isInsuranceEnabled,
                isSalaryClient
        );
    }

    public BigDecimal getTotalPayment(
            BigDecimal amount, BigDecimal rate, Integer term, BigDecimal monthlyPayment, Boolean isInsuranceEnabled
    ) {
        log.atDebug().log("getTotalPayment called at {}", LocalDateTime.now());
        BigDecimal overpayment = countOverpayment(amount, rate, term, monthlyPayment);
        return amount.add(overpayment).add(isInsuranceEnabled ? insurancePayment : BigDecimal.ZERO);
    }

    public BigDecimal getResultRate(boolean isInsuranceEnabled, boolean isSalaryClient, BigDecimal baseRate) {
        log.atDebug().log("getResultRate called at {}", LocalDateTime.now());
        BigDecimal resultRate = baseRate;
        if (isSalaryClient) {
            resultRate = resultRate.subtract(BigDecimal.ONE);
        }
        if (isInsuranceEnabled) {
            resultRate = resultRate.subtract(BigDecimal.valueOf(1.5));
        }
        return resultRate;
    }

    public BigDecimal countMonthlyPayment(BigDecimal amount, BigDecimal rate, Integer term) {
        log.atDebug().log("countMonthlyPayment called at {}", LocalDateTime.now());
        BigDecimal monthRate = countMonthRate(rate);
        BigDecimal powResult = monthRate.add(BigDecimal.ONE).pow(term);
        BigDecimal result =
                amount
                        .multiply(monthRate)
                        .multiply(powResult)
                        .divide(
                                powResult
                                        .subtract(BigDecimal.ONE),
                                ROUNDING_MODE
                        );
        return result.setScale(2, ROUNDING_MODE);
    }

    private BigDecimal countOverpayment(BigDecimal amount, BigDecimal ratePercent, Integer term, BigDecimal monthlyPayment) {
        BigDecimal result = BigDecimal.ZERO;
        BigDecimal rate = countMonthRate(ratePercent);
        for (int n = 0; n < term; n++) {
            BigDecimal curC = BigDecimal.ONE;
            BigDecimal ratePowForAmount = rate;
            BigDecimal ratePowForMonthlyPayment = BigDecimal.ONE;
            for (int k = 0; k <= n; k++) {
                result = result
                        .add(
                                curC
                                .multiply(
                                        amount
                                        .multiply(ratePowForAmount)
                                        .subtract(
                                                monthlyPayment
                                                .multiply(ratePowForMonthlyPayment)
                                        )
                                )
                        );
                curC = curC.multiply(BigDecimal.valueOf(n - k)).divide(BigDecimal.valueOf(k + 1), 6, ROUNDING_MODE);
                ratePowForAmount = ratePowForAmount.multiply(rate);
                ratePowForMonthlyPayment = ratePowForMonthlyPayment.multiply(rate);
            }
        }
        result = result.add(BigDecimal.valueOf(term).multiply(monthlyPayment));
        return result.setScale(2, ROUNDING_MODE);
    }

    public BigDecimal countMonthRate(BigDecimal rate) {
        return rate.divide(BigDecimal.valueOf(1200), 2, ROUNDING_MODE);
    }
}
