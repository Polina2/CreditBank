package ru.neoflex.edu.java.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.neoflex.edu.java.client.CalculatorClient;
import ru.neoflex.edu.java.dto.CreditDto;
import ru.neoflex.edu.java.dto.FinishRegistrationRequestDto;
import ru.neoflex.edu.java.dto.ScoringDataDto;
import ru.neoflex.edu.java.entity.Credit;
import ru.neoflex.edu.java.entity.Statement;
import ru.neoflex.edu.java.entity.enums.ApplicationStatus;
import ru.neoflex.edu.java.entity.enums.CreditStatus;
import ru.neoflex.edu.java.mapper.CreditDataMapper;
import ru.neoflex.edu.java.repository.JpaCreditRepository;
import ru.neoflex.edu.java.repository.JpaStatementRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class CalculationService {
    JpaStatementRepository statementRepository;
    JpaCreditRepository creditRepository;
    CalculatorClient calculatorClient;
    CreditDataMapper mapper;
    public void calculate(FinishRegistrationRequestDto request, String statementId) {
        Statement statement = statementRepository.findById(UUID.fromString(statementId)).orElseThrow();
        ScoringDataDto scoringDataDto = mapper
                .toScoringDataDto(request, statement.getClient(), statement.getAppliedOffer());
        CreditDto creditDto = calculatorClient.calculateCredit(scoringDataDto);
        Credit credit = mapper.toCredit(creditDto);
        credit.setCreditStatus(CreditStatus.CALCULATED);
        creditRepository.save(credit);
        log.info("Saved credit {}", credit);
        statement.setStatus(ApplicationStatus.APPROVED);
        statementRepository.save(statement);
        log.info("Saved statement {}", statement);
    }
}
