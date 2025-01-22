package ru.neoflex.edu.java.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.HttpClientErrorException;

public class CalculatorException extends HttpClientErrorException {
    public CalculatorException(String message) {
        super(HttpStatusCode.valueOf(400), message);
    }
}
