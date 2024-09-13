package com.manage_staff.dto.request;

import java.time.LocalDate;

import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class SocialInsuranceUpdateRequest {

    LocalDate beginDay;

    LocalDate expiryDate;

    String registrationArea;

    String socialInsuranceId;
}
