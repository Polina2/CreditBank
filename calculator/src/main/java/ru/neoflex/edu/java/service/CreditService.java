package ru.neoflex.edu.java.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.neoflex.edu.java.dto.CreditDto;
import ru.neoflex.edu.java.dto.PaymentScheduleElementDto;
import ru.neoflex.edu.java.dto.ScoringDataDto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CreditService {
    private final ScoringService scoringService;
    private final OfferService offerService;

    public CreditDto calculateCredit(ScoringDataDto scoringDataDto) {
        BigDecimal rate = scoringService.countResultRate(scoringDataDto);
        BigDecimal monthlyPayment =
                offerService.countMonthlyPayment(scoringDataDto.amount(), rate, scoringDataDto.term());
        List<PaymentScheduleElementDto> schedule =
                getPaymentSchedule(scoringDataDto.amount(), scoringDataDto.term(), monthlyPayment, rate);
        BigDecimal psk =
                getPsk(
                        scoringDataDto.amount(),
                        rate,
                        scoringDataDto.term(),
                        monthlyPayment,
                        scoringDataDto.isInsuranceEnabled()
                );
        return new CreditDto(
                scoringDataDto.amount(),
                scoringDataDto.term(),
                monthlyPayment,
                rate,
                psk,
                scoringDataDto.isInsuranceEnabled(),
                scoringDataDto.isSalaryClient(),
                schedule
        );
    }

    private List<PaymentScheduleElementDto> getPaymentSchedule(
            BigDecimal amount, Integer term, BigDecimal monthlyPayment, BigDecimal rate
    ) {
        List<PaymentScheduleElementDto> schedule = new ArrayList<>();
        BigDecimal monthRate = offerService.countMonthRate(rate);
        LocalDate curDate = LocalDate.now();
        BigDecimal curDebt = amount;
        schedule.add(
                new PaymentScheduleElementDto(
                        0, curDate, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, curDebt
                )
        );
        for (int number = 1; number <= term; number++) {
            curDate = curDate.plusMonths(1);
            BigDecimal totalPayment = monthlyPayment;
            BigDecimal interestPayment = curDebt.multiply(monthRate);
            BigDecimal debtPayment = monthlyPayment.subtract(interestPayment);
            if (curDebt.subtract(debtPayment).compareTo(BigDecimal.ZERO) < 0) {
                debtPayment = curDebt;
                curDebt = BigDecimal.ZERO;
                totalPayment = interestPayment.add(debtPayment);
            } else {
                curDebt = curDebt.subtract(debtPayment);
            }
            schedule.add(
                    new PaymentScheduleElementDto(
                            number, curDate, totalPayment, interestPayment, debtPayment, curDebt
                    )
            );
        }
        return schedule;
    }

    private BigDecimal getPsk(
            BigDecimal amount, BigDecimal rate, Integer term, BigDecimal monthlyPayment, Boolean isInsuranceEnabled
    ) {
        BigDecimal totalAmount = offerService.getTotalPayment(amount, rate, term, monthlyPayment, isInsuranceEnabled);
        BigDecimal result =
                totalAmount
                .divide(amount, 2, OfferService.ROUNDING_MODE)
                .subtract(BigDecimal.ONE)
                .multiply(BigDecimal.valueOf(100))
                .divide(BigDecimal.valueOf(term / 12.0), 2, OfferService.ROUNDING_MODE);
        return result;
    }
}
