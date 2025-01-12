package ru.neoflex.edu.java.service;

import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.neoflex.edu.java.client.CalculatorClient;
import ru.neoflex.edu.java.dto.CreditDto;
import ru.neoflex.edu.java.dto.EmailMessage;
import ru.neoflex.edu.java.dto.FinishRegistrationRequestDto;
import ru.neoflex.edu.java.dto.ScoringDataDto;
import ru.neoflex.edu.java.dto.enums.Theme;
import ru.neoflex.edu.java.entity.Client;
import ru.neoflex.edu.java.entity.Credit;
import ru.neoflex.edu.java.entity.Statement;
import ru.neoflex.edu.java.entity.enums.ApplicationStatus;
import ru.neoflex.edu.java.entity.enums.CreditStatus;
import ru.neoflex.edu.java.kafka.DealProducer;
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
    private final DealProducer dealProducer;

    @Value("${deal.kafka.topics.create-documents}")
    private String createDocumentsTopic;
    @Value("${deal.mail.text.create-documents}")
    private String text;

    @Transactional
    public void calculate(FinishRegistrationRequestDto request, @NotNull String statementId) {
        Statement statement = statementRepository.findById(UUID.fromString(statementId)).orElseThrow();
        Client client = statement.getClient();
        client = mapper.toClient(request, client);
        clientRepository.save(client);
        log.info("Saved client {}", client);
        ScoringDataDto scoringDataDto = mapper.toScoringDataDto(client, statement.getAppliedOffer());
//TODO: catch exception
        CreditDto creditDto = calculatorClient.calculateCredit(scoringDataDto);
        Credit credit = mapper.toCredit(creditDto);
        credit.setCreditStatus(CreditStatus.CALCULATED);
        creditRepository.save(credit);
        log.info("Saved credit {}", credit);

        statement.setStatus(ApplicationStatus.CC_APPROVED);
        statement.setCredit(credit);
        statementRepository.save(statement);
        log.info("Saved statement {}", statement);

        text = text.replace("{statementId}", statementId);
        EmailMessage emailMessage = new EmailMessage(
                client.getEmail(), Theme.CREATE_DOCUMENTS, statement.getStatementId(), text
        );
        dealProducer.sendMessage(createDocumentsTopic, emailMessage);
        log.info("Sent message {}", emailMessage);
    }
}
