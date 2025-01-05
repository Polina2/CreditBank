package ru.neoflex.edu.java.controller;

import org.springframework.web.bind.annotation.RestController;
import ru.neoflex.edu.java.dto.LoanOfferDto;
import ru.neoflex.edu.java.dto.LoanStatementRequestDto;

import java.util.List;

@RestController
public class StatementController implements StatementApi{
    @Override
    public List<LoanOfferDto> createStatement(LoanStatementRequestDto request) {
        return null;
    }

    @Override
    public void selectOffer(LoanOfferDto request) {

    }
}
