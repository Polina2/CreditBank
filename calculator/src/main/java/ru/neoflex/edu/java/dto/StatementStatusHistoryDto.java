package ru.neoflex.edu.java.dto;

import ru.neoflex.edu.java.dto.enums.ChangeType;
import ru.neoflex.edu.java.dto.enums.StatementStatus;

import java.time.LocalDateTime;

public record StatementStatusHistoryDto(
        StatementStatus status,
        LocalDateTime time,
        ChangeType changeType
) {
}
