package ru.neoflex.edu.java.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.neoflex.edu.java.client.DealClient;
import ru.neoflex.edu.java.dto.ApplicationStatusDto;

@Service
@RequiredArgsConstructor
public class DealService {
    private final DealClient dealClient;

    public void updateStatus(String statementId, ApplicationStatusDto dto) {
        dealClient.updateStatus(statementId, dto);
    }
}
