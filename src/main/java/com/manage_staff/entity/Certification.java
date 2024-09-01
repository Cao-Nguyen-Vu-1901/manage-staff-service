package com.manage_staff.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "certifications")
public class Certification  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    String name;

    LocalDate issueDate;

    String issuingAuthority;

    LocalDate expiryDate;

    String image;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "staff_id")
    @JsonIdentityReference(alwaysAsId = true)
    Staff staff;
}

