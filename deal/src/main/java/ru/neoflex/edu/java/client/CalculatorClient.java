package ru.neoflex.edu.java.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import ru.neoflex.edu.java.dto.CreditDto;
import ru.neoflex.edu.java.dto.LoanOfferDto;
import ru.neoflex.edu.java.dto.LoanStatementRequestDto;
import ru.neoflex.edu.java.dto.ScoringDataDto;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CalculatorClient {
    private final WebClient calculatorWebClient;

    public List<LoanOfferDto> getOffers(LoanStatementRequestDto request) {
        String path = "/calculator/offers";
        List<LoanOfferDto> response = calculatorWebClient
                .post()
                .uri(path)
                .retrieve()
                .bodyToFlux(LoanOfferDto.class)
                .collectList()
                .block();
        return response;
    }

    public CreditDto calculateCredit(ScoringDataDto request) {
        String path = "/calculator/calc";
        CreditDto response = calculatorWebClient
                .post()
                .uri(path)
                .retrieve()
                .bodyToMono(CreditDto.class)
                .block();
        return response;
    }
}
