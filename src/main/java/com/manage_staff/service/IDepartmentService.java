package com.manage_staff.service;


import com.manage_staff.dto.request.DepartmentRequest;
import com.manage_staff.dto.response.DepartmentResponse;

import java.util.List;

public interface IDepartmentService {
    List<DepartmentResponse> findAll();
    List<DepartmentResponse> findAllByNameLike(String name);
    DepartmentResponse findById(String id);

    DepartmentResponse save(DepartmentRequest request);

    DepartmentResponse update(String id, DepartmentRequest request);

    void deleteById(String id);

    void deleteAllById(List<String> ids);

    void deleteAll();



}
