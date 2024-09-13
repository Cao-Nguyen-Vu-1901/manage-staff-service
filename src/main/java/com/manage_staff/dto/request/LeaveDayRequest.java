package com.manage_staff.dto.request;

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
public class LeaveDayRequest {

    @NotNull
    @Size(min = 1, message = "Name must be at least 1 character")
    String name;

    @NotNull
    @DayConstraint
    LocalDate startDate;

    @NotNull
    @DayConstraint
    LocalDate endDate;

    @NotNull
    @Size(min = 1, message = "Regulation must be at least 1 character")
    String regulation;

    @NotNull
    @Size(min = 1, message = "Reason must be at least 1 character")
    String reason;
}
