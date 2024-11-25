package ru.neoflex.edu.java.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.neoflex.edu.java.dto.CreditDto;
import ru.neoflex.edu.java.dto.LoanOfferDto;
import ru.neoflex.edu.java.dto.LoanStatementRequestDto;
import ru.neoflex.edu.java.dto.ScoringDataDto;
import ru.neoflex.edu.java.service.OfferService;

import java.util.List;

@RestController
@RequestMapping("/calculator")
@RequiredArgsConstructor
public class CalculatorController implements CalculatorApi {

    private final OfferService offerService;

    @Override
    public List<LoanOfferDto> getOffers(LoanStatementRequestDto loanStatementRequestDto) {
        return offerService.getOffers(loanStatementRequestDto);
    }

    @Override
    public CreditDto calculateCredit(ScoringDataDto scoringDataDto) {
        return null;
    }
}
