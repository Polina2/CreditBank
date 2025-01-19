package ru.neoflex.edu.java.dto;

import ru.neoflex.edu.java.entity.enums.ApplicationStatus;
import ru.neoflex.edu.java.entity.enums.ChangeType;

import java.time.LocalDateTime;

public record StatementStatusHistoryDto(
        ApplicationStatus status,
        LocalDateTime time,
        ChangeType changeType
) {
}
