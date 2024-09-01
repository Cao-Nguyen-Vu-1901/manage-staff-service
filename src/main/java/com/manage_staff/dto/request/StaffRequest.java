package com.manage_staff.dto.request;

import com.manage_staff.entity.*;
<<<<<<< HEAD

import com.manage_staff.validator.DobConstraint;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import jakarta.validation.constraints.Pattern;

=======
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
>>>>>>> 4fb93866b5e43bde319c76341ed10b1a80862474
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
public class StaffRequest {

    @NotNull
    @Size(min = 2, message = "Name must be at least 2 characters")
    String name;

<<<<<<< HEAD
    @DobConstraint(min = 18)
=======
    @DobConstraint(min = 10)
>>>>>>> 4fb93866b5e43bde319c76341ed10b1a80862474
    LocalDate dob;

    String gender;

    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
            , message = "Email must be in the format example@gmail.com")
    String email;

    @Pattern(regexp = "^\\d{10}$"
            , message = "Phone number must be number and at least 10 characters")
    String phoneNumber;

    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")
    String email;

    
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

