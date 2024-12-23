package ru.neoflex.edu.java.client;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
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
        return response;
    }

    public CreditDto calculateCredit(ScoringDataDto request) {
        CreditDto response = calculatorWebClient
                .post()
                .uri(calcPath)
                .body(request)
                .retrieve()
                .body(CreditDto.class);
        return response;
    }
}
