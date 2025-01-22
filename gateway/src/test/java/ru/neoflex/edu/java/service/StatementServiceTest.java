package ru.neoflex.edu.java.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.neoflex.edu.java.client.StatementClient;
import ru.neoflex.edu.java.dto.LoanOfferDto;
import ru.neoflex.edu.java.dto.LoanStatementRequestDto;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StatementServiceTest {
    @Mock
    private StatementClient statementClient;

    @InjectMocks
    private StatementService statementService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createStatement() {
        LoanStatementRequestDto requestDto = new LoanStatementRequestDto();
        List<LoanOfferDto> expectedOffers = new ArrayList<>();
        expectedOffers.add(new LoanOfferDto());
        when(statementClient.createStatement(requestDto)).thenReturn(expectedOffers);

        List<LoanOfferDto> actualOffers = statementService.createStatement(requestDto);

        assertEquals(expectedOffers, actualOffers);
    }

    @Test
    void applyOffer() {
        LoanOfferDto offerDto = new LoanOfferDto();
        doNothing().when(statementClient).applyOffer(offerDto);

        statementService.applyOffer(offerDto);

        verify(statementClient).applyOffer(offerDto);
    }
}