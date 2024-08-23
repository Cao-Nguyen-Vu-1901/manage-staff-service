package com.manage_staff.dto.request;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class SocialInsuranceRequest {


    LocalDate beginDay;

    LocalDate expiryDate;

    String registrationArea;

    String staff;

    String socialInsuranceId;
}
