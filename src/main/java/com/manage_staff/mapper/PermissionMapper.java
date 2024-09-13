package com.manage_staff.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.manage_staff.dto.request.PermissionRequest;
import com.manage_staff.dto.response.PermissionResponse;
import com.manage_staff.entity.Permission;

@Mapper(componentModel = "spring")
public interface PermissionMapper {

    Permission toPermission(PermissionRequest request);

    PermissionResponse toPermissionResponse(Permission permission);

    void updatePermission(@MappingTarget Permission permission, PermissionRequest request);
}
