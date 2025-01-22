package ru.neoflex.edu.java.dto;

import java.time.LocalDate;

public record PassportDto(
        String series,
        String number,
        String issueBranch,
        LocalDate issueDate
) {
}
