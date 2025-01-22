package ru.neoflex.edu.java.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import ru.neoflex.edu.java.dto.FinishRegistrationRequestDto;
import ru.neoflex.edu.java.dto.SesCodeDto;

@Component
@RequiredArgsConstructor
@Slf4j
public class DealClient {
    private final RestClient dealWebClient;

    @Value("${gateway.dealClient.calculateUrl}")
    private String calculatePath;
    @Value("${gateway.dealClient.sendUrl}")
    private String sendPath;
    @Value("${gateway.dealClient.signUrl}")
    private String signPath;
    @Value("${gateway.dealClient.codeUrl}")
    private String codePath;

    public void calculateCredit(String statementId, FinishRegistrationRequestDto requestDto) {
        ResponseEntity<Void> response = dealWebClient
                .post()
                .uri(calculatePath.replace("{statementId}", statementId))
                .body(requestDto)
                .retrieve()
                .toBodilessEntity();
        log.info("Response from {}: {}", calculatePath, response);
    }

    public void sendDocuments(String statementId) {
        ResponseEntity<Void> response = dealWebClient
                .post()
                .uri(sendPath.replace("{statementId}", statementId))
                .retrieve()
                .toBodilessEntity();
        log.info("Response from {}: {}", sendPath, response);
    }

    public void signDocuments(String statementId) {
        ResponseEntity<Void> response = dealWebClient
                .post()
                .uri(signPath.replace("{statementId}", statementId))
                .retrieve()
                .toBodilessEntity();
        log.info("Response from {}: {}", signPath, response);
    }

    public void verifySesCode(String statementId, SesCodeDto sesCodeDto) {
        ResponseEntity<Void> response = dealWebClient
                .post()
                .uri(codePath.replace("{statementId}", statementId))
                .body(sesCodeDto)
                .retrieve()
                .toBodilessEntity();
        log.info("Response from {}: {}", codePath, response);
    }
}
