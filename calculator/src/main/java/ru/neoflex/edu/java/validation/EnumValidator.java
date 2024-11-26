package ru.neoflex.edu.java.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EnumValidator implements ConstraintValidator<ValidEnum, Enum<?>> {
    private String regexp;
    @Override
    public void initialize(ValidEnum constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        regexp = constraintAnnotation.regexp();
    }

    @Override
    public boolean isValid(Enum<?> anEnum, ConstraintValidatorContext constraintValidatorContext) {
        if (anEnum == null)
            return true;
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(anEnum.name());
        return matcher.matches();
    }
}
