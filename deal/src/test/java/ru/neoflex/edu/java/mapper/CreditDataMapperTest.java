package ru.neoflex.edu.java.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.neoflex.edu.java.dto.*;
import ru.neoflex.edu.java.entity.Client;
import ru.neoflex.edu.java.entity.Credit;
import ru.neoflex.edu.java.entity.enums.EmploymentPosition;
import ru.neoflex.edu.java.entity.enums.EmploymentStatus;
import ru.neoflex.edu.java.entity.enums.Gender;
import ru.neoflex.edu.java.entity.enums.MaritalStatus;
import ru.neoflex.edu.java.entity.json.Employment;
import ru.neoflex.edu.java.entity.json.LoanOffer;
import ru.neoflex.edu.java.entity.json.Passport;
import ru.neoflex.edu.java.entity.json.PaymentScheduleElement;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CreditDataMapperTest {

    private final CreditDataMapper mapper = new CreditDataMapperImpl();
    
    @Test
    void toPaymentScheduleElement() {
        PaymentScheduleElementDto dto = new PaymentScheduleElementDto(
                1,
                LocalDate.of(2022, 10, 1),
                BigDecimal.valueOf(1000),
                BigDecimal.valueOf(500),
                BigDecimal.valueOf(500),
                BigDecimal.valueOf(2500)
        );
        PaymentScheduleElement expectedPaymentScheduleElement = getPaymentScheduleElement();

        PaymentScheduleElement actualPaymentScheduleElement = mapper.toPaymentScheduleElement(dto);

        Assertions.assertEquals(expectedPaymentScheduleElement, actualPaymentScheduleElement);
    }

    private static PaymentScheduleElement getPaymentScheduleElement() {
        PaymentScheduleElement expectedPaymentScheduleElement = new PaymentScheduleElement();
        expectedPaymentScheduleElement.setNumber(1);
        expectedPaymentScheduleElement.setDate(LocalDate.of(2022, 10, 1));
        expectedPaymentScheduleElement.setTotalPayment(BigDecimal.valueOf(1000));
        expectedPaymentScheduleElement.setInterestPayment(BigDecimal.valueOf(500));
        expectedPaymentScheduleElement.setDebtPayment(BigDecimal.valueOf(500));
        expectedPaymentScheduleElement.setRemainingDebt(BigDecimal.valueOf(2500));
        return expectedPaymentScheduleElement;
    }

    @Test
    void toScoringDataDto() {
        Client client = getClient(true);

        LoanOffer offer = new LoanOffer();
        offer.setStatementId(UUID.fromString("00000000-0000-0000-0000-000000000000"));
        offer.setRequestedAmount(BigDecimal.valueOf(3000000));
        offer.setTotalAmount(BigDecimal.valueOf(3352095.86));
        offer.setTerm(12);
        offer.setMonthlyPayment(BigDecimal.valueOf(279341.32));
        offer.setRate(BigDecimal.valueOf(21));
        offer.setIsInsuranceEnabled(false);
        offer.setIsSalaryClient(false);

        ScoringDataDto expectedScoringDataDto = new ScoringDataDto(
                BigDecimal.valueOf(3000000), 12,
                "John", "Doe", "Smith",
                Gender.MALE,
                LocalDate.of(2002, 10, 1),
                "1234", "123456",
                LocalDate.of(2022, 10, 1),
                "qwerty",
                MaritalStatus.SINGLE,
                0,
                new EmploymentDto(
                        EmploymentStatus.EMPLOYED, "1234567",
                        BigDecimal.valueOf(200000), EmploymentPosition.WORKER,
                        24, 12
                ),
                "12345678",
                false, false
        );

        ScoringDataDto actualScoringDataDto = mapper.toScoringDataDto(client, offer);

        assertEquals(expectedScoringDataDto, actualScoringDataDto);
    }

    private static Client getClient(boolean isFull) {
        Client client = new Client();
        client.setFirstName("John");
        client.setLastName("Doe");
        client.setMiddleName("Smith");
        Passport passport = new Passport();
        passport.setNumber("123456");
        passport.setSeries("1234");
        client.setEmail("qq@mail.ru");
        client.setBirthDate(Date.valueOf(LocalDate.of(2002, 10,1)));
        client.setPassport(passport);
        if (!isFull) {
            return client;
        }
        client.getPassport().setIssueDate(Date.valueOf(LocalDate.of(2022, 10, 1)));
        client.getPassport().setIssueBranch("qwerty");
        client.setGender(Gender.MALE);
        client.setMaritalStatus(MaritalStatus.SINGLE);
        client.setDependentAmount(0);
        client.setAccountNumber("12345678");
        Employment employment = new Employment();
        employment.setStatus(EmploymentStatus.EMPLOYED);
        employment.setEmployerInn("1234567");
        employment.setSalary(BigDecimal.valueOf(200000));
        employment.setPosition(EmploymentPosition.WORKER);
        employment.setWorkExperienceTotal(24);
        employment.setWorkExperienceCurrent(12);
        client.setEmployment(employment);
        return client;
    }

    @Test
    void toClient() {
        FinishRegistrationRequestDto request = new FinishRegistrationRequestDto(
                Gender.MALE,
                MaritalStatus.SINGLE,
                0,
                LocalDate.of(2022, 10, 1),
                "qwerty",
                new EmploymentDto(
                        EmploymentStatus.EMPLOYED, "1234567",
                        BigDecimal.valueOf(200000), EmploymentPosition.WORKER,
                        24, 12
                ),
                "12345678"
        );
        Client client = getClient(false);

        Client expectedClient = getClient(true);

        Client actualClient = mapper.toClient(request, client);

        org.assertj.core.api.Assertions.assertThat(actualClient)
                .usingRecursiveComparison()
                .ignoringFields("birthDate", "passport")
                .isEqualTo(expectedClient);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        org.assertj.core.api.Assertions.assertThat(sdf.format(actualClient.getBirthDate()))
                .isEqualTo(sdf.format(expectedClient.getBirthDate()));
        org.assertj.core.api.Assertions.assertThat(actualClient.getPassport())
                .usingRecursiveComparison()
                .ignoringFields("issueDate")
                .isEqualTo(expectedClient.getPassport());
        org.assertj.core.api.Assertions.assertThat(sdf.format(actualClient.getPassport().getIssueDate()))
                .isEqualTo(sdf.format(expectedClient.getPassport().getIssueDate()));
    }

    @Test
    void toEmployment() {
        EmploymentDto dto = new EmploymentDto(
                EmploymentStatus.SELF_EMPLOYED,
                "123456789",
                BigDecimal.valueOf(50000),
                EmploymentPosition.MID_MANAGER,
                50,
                20
        );
        Employment expectedEmployment = new Employment();
        expectedEmployment.setStatus(EmploymentStatus.SELF_EMPLOYED);
        expectedEmployment.setEmployerInn("123456789");
        expectedEmployment.setSalary(BigDecimal.valueOf(50000));
        expectedEmployment.setPosition(EmploymentPosition.MID_MANAGER);
        expectedEmployment.setWorkExperienceTotal(50);
        expectedEmployment.setWorkExperienceCurrent(20);

        Employment actualEmployment = mapper.toEmployment(dto);

        Assertions.assertEquals(expectedEmployment, actualEmployment);
    }

    @Test
    void toEmploymentDto() {
        Employment employment = new Employment();
        employment.setStatus(EmploymentStatus.BUSINESS_OWNER);
        employment.setEmployerInn("987654321");
        employment.setSalary(BigDecimal.valueOf(300000));
        employment.setPosition(EmploymentPosition.OWNER);
        employment.setWorkExperienceTotal(30);
        employment.setWorkExperienceCurrent(10);
        EmploymentDto expectedEmploymentDto = new EmploymentDto(
                EmploymentStatus.BUSINESS_OWNER,
                "987654321",
                BigDecimal.valueOf(300000),
                EmploymentPosition.OWNER,
                30,
                10
        );

        EmploymentDto actualEmploymentDto = mapper.toEmploymentDto(employment);

        Assertions.assertEquals(expectedEmploymentDto, actualEmploymentDto);
    }

    @Test
    void toCredit() {
        CreditDto creditDto = new CreditDto(
                BigDecimal.valueOf(5000),
                12,
                BigDecimal.valueOf(500),
                BigDecimal.valueOf(20),
                BigDecimal.valueOf(25),
                true,
                false,
                new ArrayList<>()
        );
        Credit expectedCredit = new Credit();
        expectedCredit.setAmount(BigDecimal.valueOf(5000));
        expectedCredit.setTerm(12);
        expectedCredit.setMonthlyPayment(BigDecimal.valueOf(500));
        expectedCredit.setRate(BigDecimal.valueOf(20));
        expectedCredit.setPsk(BigDecimal.valueOf(25));
        expectedCredit.setInsuranceEnabled(true);
        expectedCredit.setSalaryClient(false);
        expectedCredit.setPaymentSchedule(new ArrayList<>());

        Credit actualCredit = mapper.toCredit(creditDto);

        Assertions.assertEquals(expectedCredit, actualCredit);
    }
}