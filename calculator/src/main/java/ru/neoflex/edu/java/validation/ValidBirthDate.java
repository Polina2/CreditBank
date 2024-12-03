package ru.neoflex.edu.java.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = BirthDateValidator.class)
public @interface ValidBirthDate {
    String message() default "Bad age";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    int maxAge() default Integer.MAX_VALUE;

    int minAge() default 0;
}
