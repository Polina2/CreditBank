package ru.neoflex.edu.java.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.neoflex.edu.java.client.DealClient;
import ru.neoflex.edu.java.dto.LoanOfferDto;
import ru.neoflex.edu.java.dto.LoanStatementRequestDto;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StatementService {
    private final DealClient dealClient;

    public List<LoanOfferDto> createStatement(LoanStatementRequestDto request) {
        return dealClient.getOffers(request);
    }

    public void selectOffer(LoanOfferDto offer) {
        dealClient.selectOffer(offer);
    }
}
