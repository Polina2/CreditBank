package ru.neoflex.edu.java.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.neoflex.edu.java.dto.CreditDto;
import ru.neoflex.edu.java.dto.LoanOfferDto;
import ru.neoflex.edu.java.dto.LoanStatementRequestDto;
import ru.neoflex.edu.java.dto.ScoringDataDto;
import ru.neoflex.edu.java.service.CreditService;
import ru.neoflex.edu.java.service.OfferService;
import ru.neoflex.edu.java.service.ScoringService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CalculatorController implements CalculatorApi {

    private final OfferService offerService;
    private final ScoringService scoringService;
    private final CreditService creditService;

    @Override
    public List<LoanOfferDto> getOffers(LoanStatementRequestDto loanStatementRequestDto) {
        return offerService.getOffers(loanStatementRequestDto);
    }

    @Override
    public CreditDto calculateCredit(ScoringDataDto scoringDataDto) {
        if (!scoringService.checkSalary(scoringDataDto.employment().salary(), scoringDataDto.dependentAmount(), scoringDataDto.amount()))
            throw new IllegalArgumentException("Bad salary");
        return creditService.calculateCredit(scoringDataDto);
    }
}
