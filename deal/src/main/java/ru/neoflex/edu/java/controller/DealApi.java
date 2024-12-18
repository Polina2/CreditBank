package ru.neoflex.edu.java.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.neoflex.edu.java.dto.FinishRegistrationRequestDto;
import ru.neoflex.edu.java.dto.LoanOfferDto;
import ru.neoflex.edu.java.dto.LoanStatementRequestDto;

import java.util.List;

@RequestMapping("/deal")
public interface DealApi {
    @PostMapping("/statement")
    List<LoanOfferDto> getOffers(@RequestBody @Valid LoanStatementRequestDto request);
    @PostMapping("/offer/select")
    void select(@RequestBody LoanOfferDto loanOffer);
    @PostMapping("/calculate/{statementId}")
    void finishRegistration(@RequestBody FinishRegistrationRequestDto request, @PathVariable String statementId);
}
