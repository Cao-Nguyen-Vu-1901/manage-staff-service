package com.manage_staff.dto.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.time.LocalDate;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SocialInsuranceResponse {

    String id;

    LocalDate beginDay;

    LocalDate expiryDate;

    String registrationArea;

    StaffResponse staff;

    String socialInsuranceId;
}
