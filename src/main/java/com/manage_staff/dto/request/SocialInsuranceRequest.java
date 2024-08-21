package com.manage_staff.dto.request;


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
public class SocialInsuranceRequest {

    @NotNull
    LocalDate beginDay;

    @NotNull
    LocalDate expiryDate;

    @NotNull
            @Size(min = 2, message = "Registration area must be at least 2 characters")
    String registrationArea;

    StaffRequest staff;

    String socialInsuranceId;
}
