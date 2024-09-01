package com.manage_staff.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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

    BigDecimal basicSalary;

    @JsonManagedReference(value = "payroll_position")
    @OneToMany(mappedBy = "payroll", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    List<Position> positions = new ArrayList<>();

}
