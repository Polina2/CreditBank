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
public class StatementClient {
    private final RestClient statementWebClient;

    @Value("${gateway.statementClient.offerUrl}")
    private String offerPath;
    @Value("${gateway.statementClient.statementUrl}")
    private String statementPath;

    public List<LoanOfferDto> createStatement(LoanStatementRequestDto requestDto) {
        List<LoanOfferDto> response = statementWebClient
                .post()
                .uri(statementPath)
                .body(requestDto)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
        log.info("Response from {}: {}", statementPath, response);
        return response;
    }

    public void applyOffer(LoanOfferDto request) {
        ResponseEntity<Void> response = statementWebClient
                .post()
                .uri(offerPath)
                .body(request)
                .retrieve()
                .toBodilessEntity();
        log.info("Response from {}: {}", offerPath, response);
    }
}
