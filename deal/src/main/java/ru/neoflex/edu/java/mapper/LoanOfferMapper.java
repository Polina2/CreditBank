package ru.neoflex.edu.java.mapper;

import org.mapstruct.Mapper;
import ru.neoflex.edu.java.dto.LoanOfferDto;
import ru.neoflex.edu.java.entity.json.LoanOffer;

@Mapper
public interface LoanOfferMapper {
    LoanOffer toLoanOffer(LoanOfferDto loanOfferDto);
}
