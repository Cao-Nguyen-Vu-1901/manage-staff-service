package com.manage_staff.dto.response;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.manage_staff.entity.Department;
import jakarta.persistence.*;
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
public class PositionResponse {

    String id;

    String name;

    LocalDate promotionDate;

    PayrollResponse payroll;

    List<StaffResponse> staff = new ArrayList<>();

    Department department;

}
