package ru.neoflex.edu.java.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import ru.neoflex.edu.java.dto.CreditDto;
import ru.neoflex.edu.java.dto.LoanOfferDto;
import ru.neoflex.edu.java.dto.LoanStatementRequestDto;
import ru.neoflex.edu.java.dto.ScoringDataDto;
import ru.neoflex.edu.java.exception.CalculatorException;
import ru.neoflex.edu.java.service.CreditService;
import ru.neoflex.edu.java.service.OfferService;
import ru.neoflex.edu.java.service.ScoringService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CalculatorController implements CalculatorApi {

    private final OfferService offerService;
    private final ScoringService scoringService;
    private final CreditService creditService;

    @Override
    public List<LoanOfferDto> getOffers(LoanStatementRequestDto loanStatementRequestDto) {
        log.info("/calculator/offers called with {} at {}", loanStatementRequestDto, LocalDateTime.now());
        return offerService.getOffers(loanStatementRequestDto);
    }

    @Override
    public CreditDto calculateCredit(ScoringDataDto scoringDataDto) {
        log.info("/calculator/calc called with {} at {}", scoringDataDto, LocalDateTime.now());
        if (
                !scoringService
                        .checkSalary(
                                scoringDataDto.employment().salary(),
                                scoringDataDto.dependentAmount(),
                                scoringDataDto.amount()
                        )
        ) {
            throw new CalculatorException("Bad salary");
        }
        return creditService.calculateCredit(scoringDataDto);
    }
}
