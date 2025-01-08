package ru.neoflex.edu.java.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.transaction.annotation.Transactional;
import ru.neoflex.edu.java.client.CalculatorClient;
import ru.neoflex.edu.java.dto.LoanOfferDto;
import ru.neoflex.edu.java.dto.LoanStatementRequestDto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@SpringBootTest
class StatementServiceTest extends IntegrationEnvironment {

    @Mock
    private CalculatorClient calculatorClient;

    @Autowired
    private StatementService statementService;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(statementService, "calculatorClient", calculatorClient);
    }

    private List<LoanOfferDto> getLoanOfferDtos() {
        return List.of(
                new LoanOfferDto(
                        null, BigDecimal.valueOf(3000000), BigDecimal.valueOf(3699766.88), 24,
                        BigDecimal.valueOf(154156.95), BigDecimal.valueOf(21), false, false
                ),
                new LoanOfferDto(
                        null, BigDecimal.valueOf(3000000), BigDecimal.valueOf(3664511.88), 24,
                        BigDecimal.valueOf(152687.99), BigDecimal.valueOf(20), false, true
                ),
                new LoanOfferDto(
                        null, BigDecimal.valueOf(3000000), BigDecimal.valueOf(3746935.11), 24,
                        BigDecimal.valueOf(151955.63), BigDecimal.valueOf(19.5), true, false
                ),
                new LoanOfferDto(
                        null, BigDecimal.valueOf(3000000), BigDecimal.valueOf(3711967.77), 24,
                        BigDecimal.valueOf(150498.66), BigDecimal.valueOf(18.5), true, true
                )
        );
    }

    @Test
    @Transactional
    @Rollback
    void createStatementAndGetOffers() {
        LoanStatementRequestDto requestDto = new LoanStatementRequestDto(
                BigDecimal.valueOf(3000000), 24,
                "aaa", "nnn", "qqq",
                "qq@mail.ru", LocalDate.of(2002, 10, 21),
                "1234", "123456"
        );
        List<LoanOfferDto> expectedList = getLoanOfferDtos();
        Mockito.when(calculatorClient.getOffers(Mockito.any())).thenReturn(getLoanOfferDtos());

        List<LoanOfferDto> actualList = statementService.createStatementAndGetOffers(requestDto);
        System.out.println(actualList);

        org.assertj.core.api.Assertions.assertThat(actualList)
                .usingRecursiveFieldByFieldElementComparatorIgnoringFields("statementId")
                .containsSequence(expectedList);
        Assertions.assertTrue(
                actualList
                        .stream()
                        .map(LoanOfferDto::statementId)
                        .distinct()
                        .limit(2)
                        .count() <= 1
        );
    }
}