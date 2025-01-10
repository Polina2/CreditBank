package ru.neoflex.edu.java.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.neoflex.edu.java.client.DealClient;
import ru.neoflex.edu.java.dto.LoanOfferDto;
import ru.neoflex.edu.java.dto.LoanStatementRequestDto;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class StatementServiceTest {

    @InjectMocks
    private StatementService statementService;

    @Mock
    private DealClient dealClient;

    private LoanStatementRequestDto request;
    private LoanOfferDto offer;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        request = new LoanStatementRequestDto();
        offer = new LoanOfferDto();
    }

    @Test
    void createStatement() {
        List<LoanOfferDto> expectedOffers = Arrays.asList(
                new LoanOfferDto(), new LoanOfferDto(), new LoanOfferDto(), new LoanOfferDto()
        );
        when(dealClient.getOffers(request)).thenReturn(expectedOffers);

        List<LoanOfferDto> actualOffers = statementService.createStatement(request);

        assertArrayEquals(expectedOffers.toArray(), actualOffers.toArray());
        verify(dealClient).getOffers(request);
    }

    @Test
    void selectOffer() {
        statementService.selectOffer(offer);

        verify(dealClient).selectOffer(offer);
    }
}