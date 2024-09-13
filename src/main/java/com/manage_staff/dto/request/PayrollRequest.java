package com.manage_staff.dto.request;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PayrollRequest {

    @NotNull
    @Size(min = 1, message = "Coefficient must be greater than or equal to 1")
    int coefficient; // hệ số

    @NotNull
    @Size(min = 1, message = "Basic salary must be greater than or equal to 1")
    BigDecimal basicSalary;
}
