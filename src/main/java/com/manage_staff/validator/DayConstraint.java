package com.manage_staff.validator;

import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DayConstraint {
    String message() default "Invalid date";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
