package com.manage_staff.validator;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DayValidator implements ConstraintValidator<DayConstraint, LocalDate> {

    @Override
    public void initialize(DayConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        if (Objects.isNull(value)) return true;
        long days = ChronoUnit.DAYS.between(value, LocalDate.now());
        return days >= LocalDate.now().getDayOfMonth();
    }
}
