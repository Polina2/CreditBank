package ru.neoflex.edu.java.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import ru.neoflex.edu.java.dto.ApplicationStatusDto;

@Component
@RequiredArgsConstructor
@Slf4j
public class DealClient {
    private final RestClient dealWebClient;

    @Value("${dossier.updateUrl}")
    private String updatePath;

    public void updateStatus(String statementId, ApplicationStatusDto applicationStatusDto) {
        ResponseEntity<Void> response = dealWebClient
                .put()
                .uri(updatePath.replace("{statementId}", statementId))
                .body(applicationStatusDto)
                .retrieve()
                .toBodilessEntity();
        log.info("Response from {}: {}", updatePath, response);
    }
}
