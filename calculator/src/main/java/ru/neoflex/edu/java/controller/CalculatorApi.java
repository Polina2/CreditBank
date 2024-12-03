package ru.neoflex.edu.java.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.neoflex.edu.java.dto.CreditDto;
import ru.neoflex.edu.java.dto.LoanOfferDto;
import ru.neoflex.edu.java.dto.LoanStatementRequestDto;
import ru.neoflex.edu.java.dto.ScoringDataDto;

import java.util.List;

@RequestMapping("/calculator")
public interface CalculatorApi {
    @PostMapping("/offers")
    List<LoanOfferDto> getOffers(@RequestBody @Valid LoanStatementRequestDto loanStatementRequestDto);

    @PostMapping("/calc")
    CreditDto calculateCredit(@RequestBody @Valid ScoringDataDto scoringDataDto);
}
