package com.manage_staff.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class PositionRequest {

    @NotNull
    @Size(min = 1, message = "Name must be at least 1 character")
    String name;

    String payroll;

    String department;
}
