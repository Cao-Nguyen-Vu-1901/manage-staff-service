package com.manage_staff.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class SocialInsuranceRequest {

    @NotNull
    LocalDate beginDay;

    @NotNull
    LocalDate expiryDate;

    @NotNull
    @Size(min = 2, message = "Registration area must be at least 2 characters")
    String registrationArea;

    @NotNull
    String staff;

    @NotNull
    String socialInsuranceId;
}
