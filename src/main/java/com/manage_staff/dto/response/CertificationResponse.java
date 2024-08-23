package com.manage_staff.dto.response;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

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

    LocalDate expiryDate;

    StaffResponse staff;
}

