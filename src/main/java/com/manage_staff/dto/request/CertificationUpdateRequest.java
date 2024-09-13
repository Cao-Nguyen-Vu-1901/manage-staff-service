package com.manage_staff.dto.request;

import java.time.LocalDate;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CertificationUpdateRequest {

    String name;

    LocalDate issueDate;

    String issuingAuthority;

    LocalDate expiryDate;
}
