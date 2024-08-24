package com.manage_staff.dto.request;

import com.manage_staff.entity.Department;
import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class PositionUpdateRequest {

    String name;

    String payroll;

    String department;
}
