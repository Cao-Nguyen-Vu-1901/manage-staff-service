package com.manage_staff.mapper;

import com.manage_staff.dto.request.RoleRequest;
import com.manage_staff.dto.response.RoleResponse;
import com.manage_staff.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    @Mapping(target = "permissions", ignore = true)
    Role toRole (RoleRequest request);
    RoleResponse toRoleResponse (Role role);
}
