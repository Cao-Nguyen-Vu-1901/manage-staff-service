package com.manage_staff.service;


import com.manage_staff.dto.request.RoleRequest;
import com.manage_staff.dto.response.LeaveDayResponse;
import com.manage_staff.dto.response.RoleResponse;

import java.util.List;

public interface IRoleService {
    List<RoleResponse> findAll();
    List<RoleResponse> findAllById( List<String> ids);
    List<RoleResponse> findAllByNameLike(String name);
    RoleResponse findById(String id);

    RoleResponse save(RoleRequest request);

    void deleteById(String id);

    void deleteAllById(List<String> ids);

    void deleteAll();
}
