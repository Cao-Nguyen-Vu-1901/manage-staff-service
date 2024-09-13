package com.manage_staff.service;

import java.util.List;

import com.manage_staff.dto.request.RoleRequest;
import com.manage_staff.dto.request.RoleUpdateRequest;
import com.manage_staff.dto.response.RoleResponse;

public interface IRoleService {
    List<RoleResponse> findAll();

    List<RoleResponse> findAllById(List<String> ids);

    List<RoleResponse> findAllByNameLike(String name);

    RoleResponse findById(String id);

    RoleResponse save(RoleRequest request);

    RoleResponse update(String id, RoleUpdateRequest request);

    void deleteById(String id);

    void deleteAllById(List<String> ids);

    void deleteAll();
}
