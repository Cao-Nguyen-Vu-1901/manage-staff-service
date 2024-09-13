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
public class RewardDisciplineRequest {

    @NotNull
    @Size(min = 1, message = "Name must be at least 1 character")
    String name;

    @NotNull
    @Size(min = 1, message = "Name must be at least 1 character")
    String content;

    @NotNull
    @DayConstraint
    LocalDate beginDate;

    @DayConstraint
    LocalDate expiryDate;

    @NotNull
    String creatorId;
}
