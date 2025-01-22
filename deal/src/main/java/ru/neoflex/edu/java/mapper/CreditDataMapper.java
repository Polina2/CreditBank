package ru.neoflex.edu.java.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;
import ru.neoflex.edu.java.dto.*;
import ru.neoflex.edu.java.entity.Client;
import ru.neoflex.edu.java.entity.Credit;
import ru.neoflex.edu.java.entity.json.Employment;
import ru.neoflex.edu.java.entity.json.LoanOffer;
import ru.neoflex.edu.java.entity.json.PaymentScheduleElement;

@Mapper(componentModel = "spring")
@Component
public interface CreditDataMapper {
    PaymentScheduleElement toPaymentScheduleElement(PaymentScheduleElementDto value);
    @Mapping(target = "amount", source = "offer.requestedAmount")
    @Mapping(target = "passportSeries", source = "client.passport.series")
    @Mapping(target = "passportNumber", source = "client.passport.number")
    @Mapping(target = "passportIssueDate", source = "client.passport.issueDate")
    @Mapping(target = "passportIssueBranch", source = "client.passport.issueBranch")
    @Mapping(target = "isInsuranceEnabled", source = "offer.isInsuranceEnabled")
    @Mapping(target = "isSalaryClient", source = "offer.isSalaryClient")
    ScoringDataDto toScoringDataDto(Client client, LoanOffer offer);

    @Mapping(target = "passport.issueDate", source = "request.passportIssueDate")
    @Mapping(target = "passport.issueBranch", source = "request.passportIssueBranch")
    @Mapping(target = "passport.series", source = "client.passport.series")
    @Mapping(target = "passport.number", source = "client.passport.number")
    @Mapping(target = "gender", source = "request.gender")
    @Mapping(target = "maritalStatus", source = "request.maritalStatus")
    @Mapping(target = "dependentAmount", source = "request.dependentAmount")
    @Mapping(target = "employment", source = "request.employment")
    @Mapping(target = "accountNumber", source = "request.accountNumber")
    Client toClient(FinishRegistrationRequestDto request, Client client);

    @Mapping(target = "status", source = "dto.employmentStatus")
    @Mapping(target = "employerInn", source = "dto.employerINN")
    Employment toEmployment(EmploymentDto dto);

    @Mapping(target = "employmentStatus", source = "e.status")
    @Mapping(target = "employerINN", source = "e.employerInn")
    EmploymentDto toEmploymentDto(Employment e);

    @Mapping(target = "insuranceEnabled", source = "creditDto.isInsuranceEnabled")
    @Mapping(target = "salaryClient", source = "creditDto.isSalaryClient")
    Credit toCredit(CreditDto creditDto);
}
