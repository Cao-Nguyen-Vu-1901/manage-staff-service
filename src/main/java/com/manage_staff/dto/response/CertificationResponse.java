package com.manage_staff.dto.response;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.*;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CertificationResponse {

    String id;

    String name;

    LocalDate issueDate;

    String issuingAuthority;

    String image;

    LocalDate expiryDate;

    StaffResponse staff;
}
