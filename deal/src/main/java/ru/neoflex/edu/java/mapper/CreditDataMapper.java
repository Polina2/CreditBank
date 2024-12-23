package ru.neoflex.edu.java.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.neoflex.edu.java.dto.CreditDto;
import ru.neoflex.edu.java.dto.FinishRegistrationRequestDto;
import ru.neoflex.edu.java.dto.ScoringDataDto;
import ru.neoflex.edu.java.entity.Client;
import ru.neoflex.edu.java.entity.Credit;
import ru.neoflex.edu.java.entity.json.LoanOffer;

@Mapper
public interface CreditDataMapper {
    @Mapping(target = "amount", source = "offer.requestedAmount")
    @Mapping(target = "gender", source = "request.gender")
    @Mapping(target = "passportSeries", source = "client.passport.series")
    @Mapping(target = "passportNumber", source = "client.passport.number")
    @Mapping(target = "maritalStatus", source = "request.maritalStatus")
    @Mapping(target = "employment", source = "request.employment")
    @Mapping(target = "accountNumber", source = "request.accountNumber")
    @Mapping(target = "isInsuranceEnabled", source = "offer.isInsuranceEnabled")
    @Mapping(target = "isSalaryClient", source = "offer.isSalaryClient")
    ScoringDataDto toScoringDataDto(FinishRegistrationRequestDto request, Client client, LoanOffer offer);

    @Mapping(target = "insuranceEnabled", source = "creditDto.isInsuranceEnabled")
    @Mapping(target = "salaryClient", source = "creditDto.isSalaryClient")
    Credit toCredit(CreditDto creditDto);
}
