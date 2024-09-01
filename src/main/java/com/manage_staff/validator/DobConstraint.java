package com.manage_staff.validator;

import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DobConstraint {

    int min();
    String message() default "Invalid date of birth";
    Class<?>[] groups() default {};
    Class< ? extends Payload>[] payload() default {};
}
