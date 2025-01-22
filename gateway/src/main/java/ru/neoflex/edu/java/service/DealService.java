package ru.neoflex.edu.java.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.neoflex.edu.java.client.DealClient;
import ru.neoflex.edu.java.dto.FinishRegistrationRequestDto;
import ru.neoflex.edu.java.dto.SesCodeDto;

@Service
@RequiredArgsConstructor
public class DealService {
    private final DealClient dealClient;

    public void calculateCredit(String statementId, FinishRegistrationRequestDto requestDto) {
        dealClient.calculateCredit(statementId, requestDto);
    }

    public void sendDocuments(String statementId) {
        dealClient.sendDocuments(statementId);
    }

    public void signDocuments(String statementId) {
        dealClient.signDocuments(statementId);
    }

    public void verifySesCode(String statementId, SesCodeDto sesCodeDto) {
        dealClient.verifySesCode(statementId, sesCodeDto);
    }
}
