package com.manage_staff.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@JsonIdentityInfo(generator=ObjectIdGenerators.UUIDGenerator.class, property="id")
public class Staff  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    String name;

    LocalDate dob;

    String gender;

    String email;

    String phoneNumber;

    String address;

    String username;

    String password;

    boolean status;

    String image;

    boolean accountVerified;

    int failedLoginAttempts;

    LocalDate createDate;


    @OneToMany(mappedBy = "staff", fetch = FetchType.EAGER)
    @JsonIdentityReference(alwaysAsId = true)
    List<Certification> certifications = new ArrayList<>();


    @OneToOne(mappedBy = "staff", fetch = FetchType.EAGER)
    SocialInsurance socialInsurance;

    @ManyToMany(fetch = FetchType.EAGER)
    List<RewardDiscipline> rewardDisciplines = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    List<LeaveDay> leaves = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    List<Benefit> benefits = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    Set<Role> roles = new HashSet<>();

}

