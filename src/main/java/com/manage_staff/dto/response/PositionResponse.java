package com.manage_staff.dto.response;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PositionResponse {

    String id;

    String name;

    LocalDate promotionDate;

    PayrollResponse payroll;

    List<StaffResponse> staff = new ArrayList<>();

    DepartmentResponse department;
}
