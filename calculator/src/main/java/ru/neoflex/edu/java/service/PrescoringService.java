package ru.neoflex.edu.java.service;

import org.springframework.stereotype.Service;
import ru.neoflex.edu.java.dto.LoanStatementRequestDto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class PrescoringService {
    private static final int MIN_AMOUNT = 20000;
    private static final int MIN_TERM = 6;
    private static final int MIN_AGE = 18;
    private static final String EMAIL_PATTERN = "^[a-z0-9A-Z_!#$%&'*+/=?`{|}~^.-]+@[a-z0-9A-Z.-]+$";
    private static final String PASSPORT_SERIES_PATTERN = "\\d{4}";
    private static final String PASSPORT_NUMBER_PATTERN = "\\d{6}";

    public boolean check(LoanStatementRequestDto request) {
        return checkFio(request.firstName(), request.lastName(), request.middleName())
                && checkAmount(request.amount())
                && checkTerm(request.term())
                && checkBirthDate(request.birthDate())
                && checkEmail(request.email())
                && checkPassport(request.passportSeries(), request.passportNumber());
    }

    private boolean checkFio(String firstName, String lastName, String middleName) {
        boolean isFirstNameCorrect = firstName.length() >= 2 && firstName.length() <= 30;
        boolean isLastNameCorrect = lastName.length() >= 2 && lastName.length() <= 30;
        boolean isMiddleNameCorrect = (middleName == null) || (middleName.length() >= 2 && middleName.length() <= 30);
        return isFirstNameCorrect && isLastNameCorrect && isMiddleNameCorrect;
    }

    private boolean checkAmount(BigDecimal amount) {
        return amount.compareTo(BigDecimal.valueOf(MIN_AMOUNT)) >= 0;
    }

    private boolean checkTerm(Integer term) {
        return term >= MIN_TERM;
    }

    private boolean checkBirthDate(LocalDate birthDate) {
        return Period.between(birthDate, LocalDate.now()).getYears() >= MIN_AGE;
    }

    private boolean checkEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.find();
    }

    private boolean checkPassport(String series, String number) {
        Pattern seriesPattern = Pattern.compile(PASSPORT_SERIES_PATTERN);
        Matcher seriesMatcher = seriesPattern.matcher(series);
        Pattern numberPattern = Pattern.compile(PASSPORT_NUMBER_PATTERN);
        Matcher numberMatcher = numberPattern.matcher(number);
        return seriesMatcher.find() && numberMatcher.find();
    }
}
