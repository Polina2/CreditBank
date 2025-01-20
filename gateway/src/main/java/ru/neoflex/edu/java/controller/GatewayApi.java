package ru.neoflex.edu.java.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.neoflex.edu.java.dto.FinishRegistrationRequestDto;
import ru.neoflex.edu.java.dto.LoanOfferDto;
import ru.neoflex.edu.java.dto.LoanStatementRequestDto;
import ru.neoflex.edu.java.dto.SesCodeDto;

import java.util.List;

public interface GatewayApi {
    @PostMapping("/statement")
    List<LoanOfferDto> getOffers(@RequestBody LoanStatementRequestDto request);

    @PostMapping("/statement/select")
    void selectOffer(@RequestBody LoanOfferDto request);

    @PostMapping("/statement/registration/{statementId}")
    void finishRegistration(@PathVariable String statementId, @RequestBody FinishRegistrationRequestDto request);

    @PostMapping("/document/{statementId}")
    void sendDocuments(@PathVariable String statementId);

    @PostMapping("/document/{statementId}/sign")
    void signDocuments(@PathVariable String statementId);

    @PostMapping("/document/{statementId}/sign/code")
    void confirmSign(@PathVariable String statementId, @RequestBody SesCodeDto sesCode);
}
