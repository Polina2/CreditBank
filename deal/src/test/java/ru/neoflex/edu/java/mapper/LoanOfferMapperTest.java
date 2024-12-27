package ru.neoflex.edu.java.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.neoflex.edu.java.dto.LoanOfferDto;
import ru.neoflex.edu.java.entity.json.LoanOffer;

import java.math.BigDecimal;
import java.util.UUID;

class LoanOfferMapperTest {

    private final LoanOfferMapper mapper = new LoanOfferMapperImpl();
    @Test
    void toLoanOffer() {
        LoanOfferDto dto = new LoanOfferDto(
                UUID.fromString("00000000-0000-0000-0000-000000000000"),
                BigDecimal.valueOf(3000000),
                BigDecimal.valueOf(3352095.86),
                12,
                BigDecimal.valueOf(279341.32),
                BigDecimal.valueOf(21),
                false, false
        );
        LoanOffer expectedLoanOffer = new LoanOffer();
        expectedLoanOffer.setStatementId(UUID.fromString("00000000-0000-0000-0000-000000000000"));
        expectedLoanOffer.setRequestedAmount(BigDecimal.valueOf(3000000));
        expectedLoanOffer.setTotalAmount(BigDecimal.valueOf(3352095.86));
        expectedLoanOffer.setTerm(12);
        expectedLoanOffer.setMonthlyPayment(BigDecimal.valueOf(279341.32));
        expectedLoanOffer.setRate(BigDecimal.valueOf(21));
        expectedLoanOffer.setIsInsuranceEnabled(false);
        expectedLoanOffer.setIsSalaryClient(false);

        LoanOffer actualLoanOffer = mapper.toLoanOffer(dto);

        Assertions.assertEquals(expectedLoanOffer, actualLoanOffer);
    }
}