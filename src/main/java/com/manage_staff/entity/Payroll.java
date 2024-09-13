package com.manage_staff.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "payrolls")
public class Payroll implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    int coefficient; // hệ số

    @Column(name = "basic_salary")
    BigDecimal basicSalary;

    @JsonManagedReference(value = "payroll_position")
    @OneToMany(mappedBy = "payroll", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    List<Position> positions = new ArrayList<>();
}
