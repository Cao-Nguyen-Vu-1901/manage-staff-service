package com.manage_staff.dto.request;

import jakarta.persistence.*;
<<<<<<< HEAD
import jakarta.validation.constraints.NotNull;
=======
>>>>>>> crud/admin
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class RoleRequest {

<<<<<<< HEAD
    @NotNull
            @Size(min = 2, message = "Name must be at least 2 character")
=======
>>>>>>> crud/admin
    String name;

    String description;

    Set<String> permissions = new HashSet<>();

}
