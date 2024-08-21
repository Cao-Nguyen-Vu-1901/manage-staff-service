package com.manage_staff.mapper;

import com.manage_staff.dto.request.RoleRequest;
import com.manage_staff.dto.response.RoleResponse;
import com.manage_staff.entity.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    Role toRole (RoleRequest request);
    RoleResponse toRoleResponse (Role role);
}
