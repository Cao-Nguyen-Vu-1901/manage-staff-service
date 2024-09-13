package com.manage_staff.dto.response;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PayrollResponse {

    String id;

    int coefficient; // hệ số

    BigDecimal basicSalary;

    List<PositionResponse> positions = new ArrayList<>();
}
