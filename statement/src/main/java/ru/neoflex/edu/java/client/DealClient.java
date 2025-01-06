package ru.neoflex.edu.java.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import ru.neoflex.edu.java.dto.LoanOfferDto;
import ru.neoflex.edu.java.dto.LoanStatementRequestDto;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
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
        log.info("Response from {}: {}", statementPath, response);
        return response;
    }

    public void selectOffer(LoanOfferDto request) {
        ResponseEntity<Void> response = dealWebClient
                .post()
                .uri(selectionPath)
                .body(request)
                .retrieve()
                .toBodilessEntity();
        log.info("Response from {}: {}", selectionPath, response);
    }
}
