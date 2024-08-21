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


    LocalDate dob;

    String gender;

    String address;

    String username;

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

