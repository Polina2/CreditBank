package ru.neoflex.edu.java.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.neoflex.edu.java.dto.FinishRegistrationRequestDto;
import ru.neoflex.edu.java.dto.LoanOfferDto;
import ru.neoflex.edu.java.dto.LoanStatementRequestDto;
import ru.neoflex.edu.java.service.StatementService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DealController implements DealApi{
    private final StatementService statementService;
    @Override
    public List<LoanOfferDto> getOffers(LoanStatementRequestDto request) {
        return null;
    }

    @Override
    public void select(LoanOfferDto loanOffer) {

    }

    @Override
    public void finishRegistration(FinishRegistrationRequestDto request, String statementId) {

    }
}
