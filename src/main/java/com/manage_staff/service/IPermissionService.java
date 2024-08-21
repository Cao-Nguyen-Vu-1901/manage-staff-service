package com.manage_staff.service;


import com.manage_staff.dto.request.PermissionRequest;
import com.manage_staff.dto.response.LeaveDayResponse;
import com.manage_staff.dto.response.PermissionResponse;

import java.util.List;

public interface IPermissionService {
    List<PermissionResponse> findAll();
    List<PermissionResponse> findAllByNameLike(String name);
    PermissionResponse findById(String id);

    PermissionResponse save(PermissionRequest request);

    void deleteById(String id);

    void deleteAllById(List<String> ids);

    void deleteAll();
}
