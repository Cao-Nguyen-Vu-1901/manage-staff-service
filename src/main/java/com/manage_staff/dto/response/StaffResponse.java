package com.manage_staff.dto.response;

import com.fasterxml.jackson.annotation.*;
import com.manage_staff.entity.*;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StaffResponse {

    String id;

    String name;

    LocalDate dob;

    String gender;

    String email;

    String phoneNumber;

    String address;

    String username;

    String image;

    boolean status;

    boolean accountVerified;

    int failedLoginAttempts;

    LocalDate createDate;

    LocalDate promotionDate;

    List<CertificationResponse> certifications = new ArrayList<>();

    SocialInsuranceResponse socialInsurance;

    List<RewardDisciplineResponse> rewardDisciplines = new ArrayList<>();

    List<LeaveDayResponse> leaves = new ArrayList<>();

    List<BenefitResponse> benefits = new ArrayList<>();

    Set<RoleResponse> roles = new HashSet<>();
}

