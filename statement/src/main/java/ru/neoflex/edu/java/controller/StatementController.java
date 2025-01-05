package ru.neoflex.edu.java.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.neoflex.edu.java.dto.LoanOfferDto;
import ru.neoflex.edu.java.dto.LoanStatementRequestDto;
import ru.neoflex.edu.java.service.StatementService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class StatementController implements StatementApi{
    private final StatementService statementService;

    @Override
    public List<LoanOfferDto> createStatement(LoanStatementRequestDto request) {
        return statementService.createStatement(request);
    }

    @Override
    public void selectOffer(LoanOfferDto request) {
        statementService.selectOffer(request);
    }
}
