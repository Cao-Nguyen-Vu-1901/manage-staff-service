package com.manage_staff.dto.request;

import java.util.HashSet;
import java.util.Set;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class RoleRequest {

    @NotNull
    @Size(min = 2, message = "Name must be at least 2 character")
    String name;

    String description;

    Set<String> permissions = new HashSet<>();
}
