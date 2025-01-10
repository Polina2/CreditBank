package ru.neoflex.edu.java.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.neoflex.edu.java.client.CalculatorClient;
import ru.neoflex.edu.java.dto.CreditDto;
import ru.neoflex.edu.java.dto.FinishRegistrationRequestDto;
import ru.neoflex.edu.java.dto.ScoringDataDto;
import ru.neoflex.edu.java.entity.Client;
import ru.neoflex.edu.java.entity.Credit;
import ru.neoflex.edu.java.entity.Statement;
import ru.neoflex.edu.java.entity.enums.ApplicationStatus;
import ru.neoflex.edu.java.mapper.CreditDataMapper;
import ru.neoflex.edu.java.repository.JpaClientRepository;
import ru.neoflex.edu.java.repository.JpaCreditRepository;
import ru.neoflex.edu.java.repository.JpaStatementRepository;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CalculationServiceTest {

    @InjectMocks
    private CalculationService calculationService;

    @Mock
    private JpaStatementRepository statementRepository;

    @Mock
    private JpaClientRepository clientRepository;

    @Mock
    private CalculatorClient calculatorClient;

    @Mock
    private CreditDataMapper mapper;

    @Mock
    private JpaCreditRepository creditRepository;

    private FinishRegistrationRequestDto request;
    private String statementId;
    private Statement statement;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        request = new FinishRegistrationRequestDto();
        statementId = UUID.randomUUID().toString();
        statement = new Statement();
    }

    @Test
    void calculate() {
        Client client = new Client();
        statement.setClient(client);
        when(statementRepository.findById(any(UUID.class))).thenReturn(Optional.of(statement));

        when(mapper.toClient(request, client)).thenReturn(client);

        ScoringDataDto scoringDataDto = new ScoringDataDto();
        when(mapper.toScoringDataDto(client, statement.getAppliedOffer())).thenReturn(scoringDataDto);

        CreditDto creditDto = new CreditDto();
        when(calculatorClient.calculateCredit(scoringDataDto)).thenReturn(creditDto);

        Credit credit = new Credit();
        when(mapper.toCredit(creditDto)).thenReturn(credit);

        calculationService.calculate(request, statementId);

        verify(clientRepository).save(client);
        verify(creditRepository).save(credit);
        verify(statementRepository).save(statement);
        assertEquals(ApplicationStatus.APPROVED, statement.getStatus());
        assertEquals(credit, statement.getCredit());
    }
}