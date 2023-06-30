package com.kazakov.events.mainservice.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;

public class DateInFutureValidator implements ConstraintValidator<DateInFuture, LocalDateTime> {
    Integer value;

    @Override
    public void initialize(DateInFuture constraintAnnotation) {
        value = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(LocalDateTime date, ConstraintValidatorContext cxt) {
        if (date == null) {
            return true;
        } else {
            return date.isAfter(LocalDateTime.now().plusHours(value));
        }
    }
}
