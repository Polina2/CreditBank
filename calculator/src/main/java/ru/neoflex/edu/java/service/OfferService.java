package ru.neoflex.edu.java.service;

import org.springframework.stereotype.Service;
import ru.neoflex.edu.java.dto.LoanOfferDto;
import ru.neoflex.edu.java.dto.LoanStatementRequestDto;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OfferService {
    public List<LoanOfferDto> getOffers(LoanStatementRequestDto request) {
        return null;
    }

    private BigDecimal countMonthlyPayment(BigDecimal amount, BigDecimal rate, Integer term) {
        BigDecimal monthRate = rate.divide(BigDecimal.valueOf(1200));
        BigDecimal powResult = monthRate.add(BigDecimal.ONE).pow(term);
        BigDecimal result =
                amount
                        .multiply(monthRate)
                        .multiply(powResult)
                        .divide(
                                powResult
                                        .subtract(BigDecimal.ONE)
                        );
        return result;
    }

    private BigDecimal countOverpayment(BigDecimal amount, BigDecimal rate, Integer term, BigDecimal monthlyPayment) {
        BigDecimal result = BigDecimal.ZERO;
        for (int n = 0; n < term; n++) {
            BigDecimal curC = BigDecimal.ONE;
            BigDecimal ratePowForAmount = rate;
            BigDecimal ratePowForMonthlyPayment = BigDecimal.ONE;
            for (int k = 0; k <= n; k++) {
                result = result.add(curC.multiply(amount.multiply(ratePowForAmount).subtract(monthlyPayment.multiply(ratePowForMonthlyPayment))));
                curC.multiply(BigDecimal.valueOf(n - k + 1)).divide(BigDecimal.valueOf(k));
                ratePowForAmount = ratePowForAmount.multiply(rate);
                ratePowForMonthlyPayment = ratePowForMonthlyPayment.multiply(rate);
            }
        }
        result = result.add(BigDecimal.valueOf(term));
        return result;
    }
}
