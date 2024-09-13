package com.manage_staff.dto.request;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import com.manage_staff.validator.DayConstraint;

import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class BenefitRequest {

    @NotNull
    @Size(min = 1, message = "Name must be at least 1 character")
    String name;

    @NotNull
    @Size(min = 1, message = "Money must be greater than or equal to 1")
    BigDecimal money;

    @NotNull
    @Size(min = 1, message = "Content must be at least 1 character")
    String content;

    @NotNull
    @DayConstraint
    LocalDate effectiveDate;
}
