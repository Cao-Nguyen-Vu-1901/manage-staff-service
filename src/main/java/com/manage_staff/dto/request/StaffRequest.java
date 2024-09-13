package com.manage_staff.dto.request;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import com.manage_staff.validator.DobConstraint;

import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class StaffRequest {

    @NotNull
    @Size(min = 2, message = "Name must be at least 2 characters")
    String name;

    @DobConstraint(min = 18)
    LocalDate dob;

    String gender;

    @Pattern(
            regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
            message = "Email must be in the format example@gmail.com")
    String email;

    @Pattern(regexp = "^\\d{10}$", message = "Phone number must be number and at least 10 characters")
    String phoneNumber;

    @NotNull
    @Size(min = 2, message = "Address must be at least 2 characters")
    String address;

    @NotNull
    @Size(min = 9, message = "Username must be at least 9 characters")
    String username;

    @NotNull
    @Size(min = 9, message = "Password must be at least 9 characters")
    String password;

    LocalDate promotionDate;

    Set<String> roles = new HashSet<>();
}
