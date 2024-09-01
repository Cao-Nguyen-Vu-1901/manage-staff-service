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
@Table(name = "staff")

<<<<<<< HEAD
public class Staff implements Serializable {
=======
public class Staff  implements Serializable {
>>>>>>> 4fb93866b5e43bde319c76341ed10b1a80862474
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(name = "name")
    String name;

    LocalDate dob;

    String gender;

    String email;

    @Column(name = "phone_number")
    String phoneNumber;

    String address;

    String username;

    String password;

    boolean status;

    String image;

    @Column(name = "account_verified")
    boolean accountVerified;

    @Column(name = "failed_login_attempts")
    int failedLoginAttempts;

    @Column(name = "create_date")
    LocalDate createDate;

    @Column(name = "promotion_date")
    LocalDate promotionDate;

    @OneToMany(mappedBy = "staff", fetch = FetchType.EAGER)
    @JsonIdentityReference(alwaysAsId = true)
    List<Certification> certifications = new ArrayList<>();

    @OneToOne(mappedBy = "staff", fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    SocialInsurance socialInsurance;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "staff_reward_disciplines",
            joinColumns = @JoinColumn(name = "staff_id"),
            inverseJoinColumns = @JoinColumn(name = "reward_disciplines_id"))
    List<RewardDiscipline> rewardDisciplines = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "staff_leaves",
            joinColumns = @JoinColumn(name = "staff_id"),
            inverseJoinColumns = @JoinColumn(name = "leaves_id"))
    List<LeaveDay> leaves = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    List<Benefit> benefits = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    Set<Role> roles = new HashSet<>();

}

