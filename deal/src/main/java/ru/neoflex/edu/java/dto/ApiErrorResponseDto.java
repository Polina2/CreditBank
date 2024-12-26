package ru.neoflex.edu.java.dto;

public record ApiErrorResponseDto(
        String exceptionName,
        Integer code,
        String message
) {
}
