package ru.neoflex.edu.java.dto;

import ru.neoflex.edu.java.entity.enums.ApplicationStatus;

public record ApplicationStatusDto(
        ApplicationStatus status
) {
}
