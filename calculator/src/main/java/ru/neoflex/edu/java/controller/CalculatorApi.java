package ru.neoflex.edu.java.controller;

import ru.neoflex.edu.java.dto.CreditDto;
import ru.neoflex.edu.java.dto.LoanOfferDto;
import ru.neoflex.edu.java.dto.LoanStatementRequestDto;
import ru.neoflex.edu.java.dto.ScoringDataDto;

import java.util.List;

public interface CalculatorApi {
    List<LoanOfferDto> getOffers(LoanStatementRequestDto loanStatementRequestDto);

    CreditDto calculateCredit(ScoringDataDto scoringDataDto);
}
