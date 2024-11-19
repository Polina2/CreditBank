package ru.neoflex.edu.java.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.neoflex.edu.java.dto.CreditDto;
import ru.neoflex.edu.java.dto.LoanOfferDto;
import ru.neoflex.edu.java.dto.LoanStatementRequestDto;
import ru.neoflex.edu.java.dto.ScoringDataDto;

import java.util.List;

@RestController
@RequestMapping("/calculator")
public class CalculatorController {
    @PostMapping("/offers")
    public List<LoanOfferDto> getOffers(LoanStatementRequestDto loanStatementRequestDto) {
        return null;
    }

    @PostMapping("/calc")
    public CreditDto calculateCredit(ScoringDataDto scoringDataDto) {
        return null;
    }
}
