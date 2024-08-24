package com.manage_staff.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class RoleUpdateRequest {

    String description;

    Set<String> permissions = new HashSet<>();

}
