package com.manage_staff.dto.response;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.manage_staff.entity.Position;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PayrollResponse {

    String id;

    int coefficient; // hệ số

    BigDecimal basicSalary;

    List<Position> positions = new ArrayList<>();
}
