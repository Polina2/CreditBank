package ru.neoflex.edu.java.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import ru.neoflex.edu.java.dto.LoanStatementRequestDto;
import ru.neoflex.edu.java.entity.Client;
import ru.neoflex.edu.java.entity.json.Passport;

@Mapper(componentModel = "spring")
@Component
public interface LoanStatementRequestMapper {
    Passport toPassport(LoanStatementRequestDto loanStatementRequestDto);
    Client toClient(LoanStatementRequestDto loanStatementRequestDto);
}
