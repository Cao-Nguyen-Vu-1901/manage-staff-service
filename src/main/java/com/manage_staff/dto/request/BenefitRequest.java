package com.manage_staff.dto.request;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class BenefitRequest {

    String name;

    BigDecimal money;

    String content;

    LocalDate effectiveDate;

}
