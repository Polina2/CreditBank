package ru.neoflex.edu.java.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;
import ru.neoflex.edu.java.dto.CreditDto;
import ru.neoflex.edu.java.dto.LoanOfferDto;
import ru.neoflex.edu.java.dto.LoanStatementRequestDto;
import ru.neoflex.edu.java.dto.ScoringDataDto;
import ru.neoflex.edu.java.exception.CalculatorException;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class CalculatorClient {
    private final RestClient calculatorWebClient;
    @Value("${deal.calculatorOffersUrl}")
    private String offersPath;
    @Value("${deal.calculatorCalcUrl}")
    private String calcPath;

    public List<LoanOfferDto> getOffers(LoanStatementRequestDto request) {
        List<LoanOfferDto> response = calculatorWebClient
                .post()
                .uri(offersPath)
                .body(request)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
        log.info("Response from {}: {}", offersPath, response);
        return response;
    }

    public CreditDto calculateCredit(ScoringDataDto request) {
        try {
            CreditDto response = calculatorWebClient
                    .post()
                    .uri(calcPath)
                    .body(request)
                    .retrieve()
                    .body(CreditDto.class);
            log.info("Response from {}: {}", calcPath, response);
            return response;
        } catch (HttpClientErrorException e) {
            throw new CalculatorException(e.getMessage());
        }
    }
}
