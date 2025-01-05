package ru.neoflex.edu.java.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import ru.neoflex.edu.java.dto.FinishRegistrationRequestDto;
import ru.neoflex.edu.java.dto.LoanOfferDto;
import ru.neoflex.edu.java.dto.LoanStatementRequestDto;
import ru.neoflex.edu.java.service.CalculationService;
import ru.neoflex.edu.java.service.SelectionService;
import ru.neoflex.edu.java.service.StatementService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class DealController implements DealApi {
    private final StatementService statementService;
    private final SelectionService selectionService;
    private final CalculationService calculationService;

    @Override
    public List<LoanOfferDto> getOffers(LoanStatementRequestDto request) {
        log.info("/deal/statement called with {}", request);
        return statementService.createStatementAndGetOffers(request);
    }

    @Override
    public void select(LoanOfferDto loanOffer) {
        log.info("/deal/offer/select called with {}", loanOffer);
        selectionService.select(loanOffer);
    }

    @Override
    public void finishRegistration(FinishRegistrationRequestDto request, String statementId) {
        log.info("/deal/calculate/{} called with {}", statementId, request);
        calculationService.calculate(request, statementId);
    }
}
