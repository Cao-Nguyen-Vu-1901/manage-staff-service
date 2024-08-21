package com.manage_staff.dto.request;

import com.manage_staff.entity.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class StaffRequest {

    @NotNull
            @Size(min = 2, message = "Name must be at least 2 characters")
    String name;

    @DobConstraint(min = 10)
    LocalDate dob;

    String gender;

    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")
    String email;

    @Pattern(regexp = "^\\d{10}$")
    String phoneNumber;
    
    @NotNull
            @Size(min = 2, message = "Name must be at least 2 characters")
    String address;

    @NotNull
            @Size(min = 9, message = "Name must be at least 9 characters")
    String username;

    @NotNull
            @Size(min = 9, message = "Name must be at least 9 characters")
    String password;

    boolean status;

    boolean accountVerified;

    int failedLoginAttempts;

    LocalDate createDate;

    List<Certification> certifications = new ArrayList<>();

    SocialInsurance socialInsurance;

    List<RewardDiscipline> rewardDisciplines = new ArrayList<>();

    List<LeaveDay> leaves = new ArrayList<>();

    List<Benefit> benefits = new ArrayList<>();

    Set<RoleRequest> roles = new HashSet<>();
}

