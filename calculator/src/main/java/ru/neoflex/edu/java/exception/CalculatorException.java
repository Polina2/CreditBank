package ru.neoflex.edu.java.exception;

public class CalculatorException extends RuntimeException {
    public CalculatorException(String message) {
        super(message);
    }

    public CalculatorException(Throwable cause) {
        super(cause);
    }

    public CalculatorException() {
        super();
    }
}
