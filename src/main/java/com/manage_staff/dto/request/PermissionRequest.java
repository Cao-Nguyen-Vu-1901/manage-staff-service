package com.manage_staff.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class PermissionRequest {

    @NotNull
    @Size(min = 2, message = "Name must be at least 2 character")
    String name;

    String description;
}
