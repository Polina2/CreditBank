package ru.neoflex.edu.java.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.neoflex.edu.java.client.StatementClient;
import ru.neoflex.edu.java.dto.LoanOfferDto;
import ru.neoflex.edu.java.dto.LoanStatementRequestDto;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StatementService {
    private final StatementClient statementClient;

    public List<LoanOfferDto> createStatement(LoanStatementRequestDto requestDto) {
        return statementClient.createStatement(requestDto);
    }

    public void applyOffer(LoanOfferDto request) {
        statementClient.applyOffer(request);
    }
}
