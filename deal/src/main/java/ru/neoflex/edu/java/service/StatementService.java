package ru.neoflex.edu.java.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.neoflex.edu.java.client.CalculatorClient;
import ru.neoflex.edu.java.dto.LoanOfferDto;
import ru.neoflex.edu.java.dto.LoanStatementRequestDto;
import ru.neoflex.edu.java.entity.Client;
import ru.neoflex.edu.java.entity.Statement;
import ru.neoflex.edu.java.entity.enums.ApplicationStatus;
import ru.neoflex.edu.java.mapper.LoanStatementRequestMapper;
import ru.neoflex.edu.java.repository.JpaClientRepository;
import ru.neoflex.edu.java.repository.JpaStatementRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class StatementService {
    private final JpaClientRepository clientRepository;
    private final JpaStatementRepository statementRepository;
    private final CalculatorClient calculatorClient;
    private final LoanStatementRequestMapper mapper;

    @Transactional
    public List<LoanOfferDto> createStatementAndGetOffers(LoanStatementRequestDto request) {
        Client client = mapper.toClient(request);
        client = clientRepository.save(client);
        log.info("Saved client {}", client);

        Statement statement = new Statement()
                .setClient(client)
                .setCreationDate(Timestamp.valueOf(LocalDateTime.now()))
                .setStatus(ApplicationStatus.PREAPPROVAL)
                .setStatusHistory(new ArrayList<>());
        Statement savedStatement = statementRepository.save(statement);
        log.info("Saved statement {}", savedStatement);

        List<LoanOfferDto> response = calculatorClient
                .getOffers(request)
                .stream()
                .map(loanOfferDto -> loanOfferDto.builder().statementId(savedStatement.getStatementId()).build())
                .toList();
        log.info("LoanOfferDto list with statementIds {}", response);
        return response;
    }
}
