package ru.neoflex.edu.java.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;
import ru.neoflex.edu.java.dto.LoanStatementRequestDto;
import ru.neoflex.edu.java.entity.Client;

@Mapper(componentModel = "spring")
@Component
public interface LoanStatementRequestMapper {
    @Mapping(target = "passport.series", source = "dto.passportSeries")
    @Mapping(target = "passport.number", source = "dto.passportNumber")
    Client toClient(LoanStatementRequestDto dto);
}
