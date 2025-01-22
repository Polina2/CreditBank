package ru.neoflex.edu.java.dto;

import ru.neoflex.edu.java.entity.enums.ApplicationStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record StatementEntityDto(
        UUID statementId,
        ClientEntityDto client,
        CreditEntityDto credit,
        ApplicationStatus status,
        LocalDateTime creationDate,
        LoanOfferDto appliedOffer,
        LocalDateTime signDate,
        String sesCode,
        List<StatementStatusHistoryDto> statusHistory
) {
}
