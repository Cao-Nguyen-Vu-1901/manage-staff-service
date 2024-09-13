package com.manage_staff.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import com.manage_staff.validator.DayConstraint;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CertificationRequest {

    @NotNull
    @Size(min = 1, message = "Name must be at least 1 character")
    String name;

    @NotNull
    LocalDate issueDate;

    @NotNull
    @Size(min = 1, message = "Issuing Authority must be at least 1 character")
    String issuingAuthority;

    @NotNull
    @DayConstraint
    LocalDate expiryDate;

    String staff;

    String image;
}
