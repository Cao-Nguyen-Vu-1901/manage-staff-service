package com.manage_staff.dto.request;

import com.manage_staff.entity.Department;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class PositionRequest {

    String name;

    LocalDate promotionDate;

    PayrollRequest payroll;

    List<StaffRequest> staff = new ArrayList<>();

    Department department;

}
