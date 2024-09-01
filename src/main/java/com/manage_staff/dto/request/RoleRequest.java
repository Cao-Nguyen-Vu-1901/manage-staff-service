package com.manage_staff.dto.request;

<<<<<<< HEAD
import jakarta.validation.constraints.NotNull;
=======
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

>>>>>>> 4fb93866b5e43bde319c76341ed10b1a80862474
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class RoleRequest {

    @NotNull
    @Size(min = 2, message = "Name must be at least 2 character")
<<<<<<< HEAD

=======
>>>>>>> 4fb93866b5e43bde319c76341ed10b1a80862474
    String name;

    String description;

    Set<String> permissions = new HashSet<>();

}
