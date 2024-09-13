package com.manage_staff.entity;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.*;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "certifications")
public class Certification implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    String name;

    @Column(name = "issue_date")
    LocalDate issueDate;

    @Column(name = "issuing_authority")
    String issuingAuthority;

    @Column(name = "expiry_date")
    LocalDate expiryDate;

    String image;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "staff_id")
    @JsonIdentityReference(alwaysAsId = true)
    Staff staff;
}
