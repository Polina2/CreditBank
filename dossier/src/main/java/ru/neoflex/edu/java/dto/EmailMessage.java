package ru.neoflex.edu.java.dto;

import ru.neoflex.edu.java.dto.enums.Theme;

import java.util.UUID;

public record EmailMessage(
        String address,
        Theme theme,
        UUID statementId,
        String text
) {
}
