package com.manage_staff.dto.request;

import java.util.HashSet;
import java.util.Set;

import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class RoleUpdateRequest {

    String description;

    Set<String> permissions = new HashSet<>();
}
