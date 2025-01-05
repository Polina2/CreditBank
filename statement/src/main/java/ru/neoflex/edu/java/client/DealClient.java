package ru.neoflex.edu.java.client;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import ru.neoflex.edu.java.dto.LoanOfferDto;
import ru.neoflex.edu.java.dto.LoanStatementRequestDto;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DealClient {
    private final RestClient dealWebClient;
    @Value("${statement.statementUrl}")
    private String statementPath;
    @Value("${statement.selectionUrl}")
    private String selectionPath;

    public List<LoanOfferDto> getOffers(LoanStatementRequestDto request) {
        List<LoanOfferDto> response = dealWebClient
                .post().uri(statementPath)
                .body(request)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
        return response;
    }

    public void selectOffer(LoanOfferDto request) {
        dealWebClient
                .post()
                .uri(selectionPath)
                .body(request)
                .retrieve()
                .toBodilessEntity();
    }
}
