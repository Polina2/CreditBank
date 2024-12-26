package ru.neoflex.edu.java.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import ru.neoflex.edu.java.dto.LoanOfferDto;
import ru.neoflex.edu.java.entity.json.LoanOffer;

@Mapper(componentModel = "spring")
@Component
public interface LoanOfferMapper {
    LoanOffer toLoanOffer(LoanOfferDto dto);
}
