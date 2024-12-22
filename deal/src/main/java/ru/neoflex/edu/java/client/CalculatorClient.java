package ru.neoflex.edu.java.client;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import ru.neoflex.edu.java.dto.CreditDto;
import ru.neoflex.edu.java.dto.LoanOfferDto;
import ru.neoflex.edu.java.dto.LoanStatementRequestDto;
import ru.neoflex.edu.java.dto.ScoringDataDto;

import java.lang.reflect.Type;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CalculatorClient {
    private final RestClient calculatorWebClient;

    public List<LoanOfferDto> getOffers(LoanStatementRequestDto request) {
        String path = "/calculator/offers";
        List<LoanOfferDto> response = calculatorWebClient
                .post()
                .uri(path)
                .body(request)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
        return response;
    }

    public CreditDto calculateCredit(ScoringDataDto request) {
        String path = "/calculator/calc";
        CreditDto response = calculatorWebClient
                .post()
                .uri(path)
                .body(request)
                .retrieve()
                .body(CreditDto.class);
        return response;
    }
}
