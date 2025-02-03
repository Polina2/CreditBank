package ru.neoflex.edu.java.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;
import ru.neoflex.edu.java.dto.LoanOfferDto;
import ru.neoflex.edu.java.entity.Statement;
import ru.neoflex.edu.java.entity.json.LoanOffer;
import ru.neoflex.edu.java.repository.JpaStatementRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SelectionServiceTest extends IntegrationEnvironment {

    @Autowired
    SelectionService selectionService;

    @Autowired
    JpaStatementRepository statementRepository;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(selectionService, "statementRepository", statementRepository);
    }

    private LoanOfferDto getLoanOfferDto() {
        return new LoanOfferDto(
                UUID.randomUUID(), BigDecimal.valueOf(3000000), BigDecimal.valueOf(3664511.88), 24,
                BigDecimal.valueOf(152687.99), BigDecimal.valueOf(20), false, true
        );
    }

    private boolean isLoanOfferEqualsToLoanOfferDto(LoanOffer loanOffer, LoanOfferDto loanOfferDto) {
        return (loanOffer != null) && (loanOfferDto != null) &&
                loanOffer.getStatementId().equals(loanOfferDto.statementId()) &&
                loanOffer.getRequestedAmount().equals(loanOfferDto.requestedAmount()) &&
                loanOffer.getTotalAmount().equals(loanOfferDto.totalAmount()) &&
                loanOffer.getTerm().equals(loanOfferDto.term()) &&
                loanOffer.getRate().equals(loanOfferDto.rate()) &&
                loanOffer.getIsInsuranceEnabled().equals(loanOfferDto.isInsuranceEnabled()) &&
                loanOffer.getIsSalaryClient().equals(loanOfferDto.isSalaryClient());
    }

    @Test
    void select() {
        Statement statement = new Statement();
        statement.setStatusHistory(new ArrayList<>());
        statement = statementRepository.save(statement);
        LoanOfferDto expectedLoanOfferDto = getLoanOfferDto().builder().statementId(statement.getStatementId()).build();

        selectionService.select(expectedLoanOfferDto);
        Optional<Statement> actualStatementOptional = statementRepository.findById(expectedLoanOfferDto.statementId());

        assertTrue(actualStatementOptional.isPresent());
        assertNotNull(actualStatementOptional.get().getAppliedOffer());
        assertTrue(
                isLoanOfferEqualsToLoanOfferDto(actualStatementOptional.get().getAppliedOffer(), expectedLoanOfferDto)
        );
    }
}