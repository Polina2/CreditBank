package ru.neoflex.edu.java.service;

import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.neoflex.edu.java.client.CalculatorClient;
import ru.neoflex.edu.java.dto.CreditDto;
import ru.neoflex.edu.java.dto.FinishRegistrationRequestDto;
import ru.neoflex.edu.java.dto.ScoringDataDto;
import ru.neoflex.edu.java.entity.Client;
import ru.neoflex.edu.java.entity.Credit;
import ru.neoflex.edu.java.entity.Statement;
import ru.neoflex.edu.java.entity.enums.ApplicationStatus;
import ru.neoflex.edu.java.entity.enums.CreditStatus;
import ru.neoflex.edu.java.mapper.CreditDataMapper;
import ru.neoflex.edu.java.repository.JpaClientRepository;
import ru.neoflex.edu.java.repository.JpaCreditRepository;
import ru.neoflex.edu.java.repository.JpaStatementRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class CalculationService {
    private final JpaStatementRepository statementRepository;
    private final JpaCreditRepository creditRepository;
    private final JpaClientRepository clientRepository;
    private final CalculatorClient calculatorClient;
    private final CreditDataMapper mapper;

    @Transactional
    public void calculate(FinishRegistrationRequestDto request, @NotNull String statementId) {
        Statement statement = statementRepository.findById(UUID.fromString(statementId)).orElseThrow();
        Client client = statement.getClient();
        client = mapper.toClient(request, client);
        clientRepository.save(client);
        log.info("Saved client {}", client);
        ScoringDataDto scoringDataDto = mapper.toScoringDataDto(client, statement.getAppliedOffer());

        CreditDto creditDto = calculatorClient.calculateCredit(scoringDataDto);
        Credit credit = mapper.toCredit(creditDto);
        credit.setCreditStatus(CreditStatus.CALCULATED);
        creditRepository.save(credit);
        log.info("Saved credit {}", credit);

        statement.setStatus(ApplicationStatus.APPROVED);
        statement.setCredit(credit);
        statementRepository.save(statement);
        log.info("Saved statement {}", statement);
    }
}
