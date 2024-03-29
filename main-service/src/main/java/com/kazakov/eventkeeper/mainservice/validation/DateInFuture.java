package com.kazakov.eventkeeper.mainservice.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = DateInFutureValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DateInFuture {
    String message() default "For the requested operation the conditions are not met.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    int value();
}
