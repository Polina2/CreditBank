package ru.neoflex.edu.java.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.neoflex.edu.java.dto.LoanOfferDto;
import ru.neoflex.edu.java.entity.Statement;
import ru.neoflex.edu.java.entity.enums.ApplicationStatus;
import ru.neoflex.edu.java.mapper.LoanOfferMapper;
import ru.neoflex.edu.java.repository.JpaStatementRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class SelectionService {
    private final JpaStatementRepository statementRepository;
    private final LoanOfferMapper mapper;

    public void select(LoanOfferDto request) {
        Statement statement = statementRepository.findById(request.statementId()).orElseThrow();
        statement.setStatus(ApplicationStatus.PREAPPROVAL);
        statement.setAppliedOffer(mapper.toLoanOffer(request));
        statementRepository.save(statement);
        log.info("Saved statement {}", statement);
    }
}
