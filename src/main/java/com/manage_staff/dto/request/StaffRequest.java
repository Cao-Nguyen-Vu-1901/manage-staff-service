package com.manage_staff.dto.request;

import com.manage_staff.entity.*;
import jakarta.validation.constraints.Pattern;
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

    String name;

    LocalDate dob;

    String gender;

    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")
    String email;

    @Pattern(regexp = "^\\d{10}$")
    String phoneNumber;

    String address;

    String username;

    String password;

    boolean status;

    boolean accountVerified;

    int failedLoginAttempts;

    LocalDate createDate;

    List<String> certifications = new ArrayList<>();

    String socialInsurance;

    List<String> rewardDisciplines = new ArrayList<>();

    List<String> leaves = new ArrayList<>();

    List<String> benefits = new ArrayList<>();

    Set<String> roles = new HashSet<>();
}

