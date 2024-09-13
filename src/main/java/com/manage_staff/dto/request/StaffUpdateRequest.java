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
public class StaffUpdateRequest {

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

    @Pattern(regexp = "^\\d{10}$", message = "Phone number must be numbers")
    String phoneNumber;

    String address;

    LocalDate promotionDate;

    Set<String> roles = new HashSet<>();
}
