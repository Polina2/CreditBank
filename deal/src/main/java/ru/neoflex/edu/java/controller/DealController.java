package ru.neoflex.edu.java.controller;

import org.springframework.web.bind.annotation.RestController;
import ru.neoflex.edu.java.dto.FinishRegistrationRequestDto;
import ru.neoflex.edu.java.dto.LoanOfferDto;
import ru.neoflex.edu.java.dto.LoanStatementRequestDto;

import java.util.List;

@RestController
public class DealController implements DealApi{
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
