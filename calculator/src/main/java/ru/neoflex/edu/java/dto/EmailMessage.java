package ru.neoflex.edu.java.dto;

import ru.neoflex.edu.java.dto.enums.Theme;

public record EmailMessage(
        String address,
        Theme theme,
        Long statementId,
        String text
) {
}
