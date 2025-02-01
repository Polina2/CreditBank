package ru.neoflex.edu.java.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import ru.neoflex.edu.java.dto.FinishRegistrationRequestDto;
import ru.neoflex.edu.java.dto.LoanOfferDto;
import ru.neoflex.edu.java.dto.LoanStatementRequestDto;
import ru.neoflex.edu.java.dto.SesCodeDto;
import ru.neoflex.edu.java.service.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class DealController implements DealApi {
    private final StatementService statementService;
    private final SelectionService selectionService;
    private final CalculationService calculationService;
    private final DocumentsService documentsService;
    private final SesCodeService sesCodeService;
    private final CreditIssueService creditIssueService;

    @Override
    public List<LoanOfferDto> getOffers(LoanStatementRequestDto request) {
        log.info("/deal/statement called with {}", request);
        return statementService.createStatementAndGetOffers(request);
    }

    @Override
    public void select(LoanOfferDto loanOffer) {
        log.info("/deal/offer/select called with {}", loanOffer);
        selectionService.select(loanOffer);
        selectionService.sendEmailMessage(loanOffer);
    }

    @Override
    public void finishRegistration(FinishRegistrationRequestDto request, String statementId) {
        log.info("/deal/calculate/{} called with {}", statementId, request);
        calculationService.calculate(request, statementId);
    }

    @Override
    public void sendDocuments(String statementId) {
        log.info("/deal/document/{}/send called", statementId);
        documentsService.createDocuments(statementId);
    }

    @Override
    public void signDocuments(String statementId) {
        log.info("/deal/document/{}/sign called", statementId);
        sesCodeService.sendSesCode(statementId);
    }

    @Override
    public void confirmSign(String statementId, SesCodeDto sesCode) {
        log.info("/deal/document/{}/code called with ses-code {}", statementId, sesCode);
        creditIssueService.signDocuments(statementId, sesCode.sesCode());
    }
}
