package ru.neoflex.edu.java.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;
import ru.neoflex.edu.java.dto.*;
import ru.neoflex.edu.java.entity.Client;
import ru.neoflex.edu.java.entity.Credit;
import ru.neoflex.edu.java.entity.Statement;
import ru.neoflex.edu.java.entity.json.Passport;
import ru.neoflex.edu.java.entity.json.StatusHistory;

@Mapper(componentModel = "spring", uses = {LoanOfferMapper.class, CreditDataMapper.class})
@Component
public interface EntityDtoMapper {
    StatementEntityDto toStatementEntityDto(Statement statement);
    ClientEntityDto toClientEntityDto(Client client);
    @Mapping(target = "isInsuranceEnabled", source = "credit.insuranceEnabled")
    @Mapping(target = "isSalaryClient", source = "credit.salaryClient")
    CreditEntityDto toCreditEntityDto(Credit credit);
    StatementStatusHistoryDto toStatementStatusHistoryDto(StatusHistory statusHistory);
    PassportDto toPassportDto(Passport passport);
}
