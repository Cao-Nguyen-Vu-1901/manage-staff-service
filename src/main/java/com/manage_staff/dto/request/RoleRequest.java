package com.manage_staff.dto.request;

import jakarta.persistence.*;
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

    String name;

    String description;

    Set<String> permissions = new HashSet<>();

}
