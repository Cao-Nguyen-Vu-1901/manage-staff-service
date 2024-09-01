package com.manage_staff.dto.request;

import com.manage_staff.validator.DayConstraint;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;


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
