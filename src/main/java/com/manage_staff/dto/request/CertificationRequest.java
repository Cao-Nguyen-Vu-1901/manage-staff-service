package com.manage_staff.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CertificationRequest {

    String name;

    LocalDate issueDate;

    String issuingAuthority;

    LocalDate expiryDate;

    String staff;

    String image;
}

