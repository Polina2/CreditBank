package ru.neoflex.edu.java.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.neoflex.edu.java.dto.ApiErrorResponseDto;

@RestControllerAdvice
public class DealExceptionHandler {
    @ExceptionHandler({RuntimeException.class, MethodArgumentNotValidException.class})
    public ResponseEntity<ApiErrorResponseDto> handleBusinessException(Exception e) {
        return new ResponseEntity<>(
                new ApiErrorResponseDto(e.getClass().getCanonicalName(), HttpStatus.BAD_REQUEST.value(), e.getMessage()),
                HttpStatus.BAD_REQUEST
        );
    }
}
