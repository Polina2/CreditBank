package ru.neoflex.edu.java.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.neoflex.edu.java.client.DealClient;
import ru.neoflex.edu.java.dto.FinishRegistrationRequestDto;
import ru.neoflex.edu.java.dto.SesCodeDto;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

class DealServiceTest {
    @Mock
    private DealClient dealClient;

    @InjectMocks
    private DealService dealService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void calculateCredit() {
        String statementId = "12345";
        FinishRegistrationRequestDto requestDto = new FinishRegistrationRequestDto();
        doNothing().when(dealClient).calculateCredit(statementId, requestDto);

        dealService.calculateCredit(statementId, requestDto);

        verify(dealClient).calculateCredit(statementId, requestDto);
    }

    @Test
    void sendDocuments() {
        String statementId = "12345";
        doNothing().when(dealClient).sendDocuments(statementId);

        dealService.sendDocuments(statementId);

        verify(dealClient).sendDocuments(statementId);
    }

    @Test
    void signDocuments() {
        String statementId = "12345";
        doNothing().when(dealClient).signDocuments(statementId);

        dealService.signDocuments(statementId);

        verify(dealClient).signDocuments(statementId);
    }

    @Test
    void verifySesCode() {
        String statementId = "12345";
        SesCodeDto sesCodeDto = new SesCodeDto("123456");
        doNothing().when(dealClient).verifySesCode(statementId, sesCodeDto);

        dealService.verifySesCode(statementId, sesCodeDto);

        verify(dealClient).verifySesCode(statementId, sesCodeDto);
    }
}