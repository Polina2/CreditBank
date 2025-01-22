package ru.neoflex.edu.java.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import ru.neoflex.edu.java.dto.FinishRegistrationRequestDto;
import ru.neoflex.edu.java.dto.LoanOfferDto;
import ru.neoflex.edu.java.dto.LoanStatementRequestDto;
import ru.neoflex.edu.java.dto.SesCodeDto;
import ru.neoflex.edu.java.service.DealService;
import ru.neoflex.edu.java.service.StatementService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class GatewayController implements GatewayApi {
    private final StatementService statementService;
    private final DealService dealService;

    @Override
    public List<LoanOfferDto> getOffers(LoanStatementRequestDto request) {
        log.info("/statement called with {}", request);
        return statementService.createStatement(request);
    }

    @Override
    public void selectOffer(LoanOfferDto request) {
        log.info("/statement/select called with {}", request);
        statementService.applyOffer(request);
    }

    @Override
    public void finishRegistration(String statementId, FinishRegistrationRequestDto request) {
        log.info("/statement/registration/{} called with {}", statementId, request);
        dealService.calculateCredit(statementId, request);
    }

    @Override
    public void sendDocuments(String statementId) {
        log.info("/document/{} called", statementId);
        dealService.sendDocuments(statementId);
    }

    @Override
    public void signDocuments(String statementId) {
        log.info("/document/{}/sign called", statementId);
        dealService.signDocuments(statementId);
    }

    @Override
    public void confirmSign(String statementId, SesCodeDto sesCode) {
        log.info("/document/{}/sign/code called with {}", statementId, sesCode);
        dealService.verifySesCode(statementId, sesCode);
    }
}
