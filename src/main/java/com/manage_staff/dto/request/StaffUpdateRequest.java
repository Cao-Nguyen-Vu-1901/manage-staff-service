package com.manage_staff.dto.request;

import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

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
public class StaffUpdateRequest {

    String name;

    LocalDate dob;

    String gender;

    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
            , message = "Email must be in the format example@gmail.com")
    String email;

    @Pattern(regexp = "^\\d{10}$"
            , message = "Phone number must be numbers")
    String phoneNumber;

    MultipartFile image;

    String address;

    String password;

    LocalDate promotionDate;

    List<String> certifications = new ArrayList<>();

    String socialInsurance;

    List<String> rewardDisciplines = new ArrayList<>();

    List<String> leaves = new ArrayList<>();

    List<String> benefits = new ArrayList<>();

    Set<String> roles = new HashSet<>();
}
