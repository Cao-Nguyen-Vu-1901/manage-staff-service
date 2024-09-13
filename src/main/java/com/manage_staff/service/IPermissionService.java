package com.manage_staff.service;

import java.util.List;

import com.manage_staff.dto.request.PermissionRequest;
import com.manage_staff.dto.response.PermissionResponse;

public interface IPermissionService {
    List<PermissionResponse> findAll();

    List<PermissionResponse> findAllById(List<String> ids);

    List<PermissionResponse> findAllByNameLike(String name);

    PermissionResponse findById(String id);

    PermissionResponse save(PermissionRequest request);

    PermissionResponse update(String id, PermissionRequest request);

    void deleteById(String id);

    void deleteAllById(List<String> ids);

    void deleteAll();
}
