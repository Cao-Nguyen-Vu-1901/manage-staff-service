package com.manage_staff.service;


import com.manage_staff.dto.request.DepartmentRequest;
import com.manage_staff.dto.request.LeaveDayRequest;
import com.manage_staff.dto.response.DepartmentResponse;
import com.manage_staff.dto.response.LeaveDayResponse;

import java.util.List;

public interface ILeaveDayService {
    List<LeaveDayResponse> findAll();
    List<LeaveDayResponse> findAllByNameLike(String name);
    LeaveDayResponse findById(String id);

    LeaveDayResponse save(LeaveDayRequest request);

    void deleteById(String id);

    void deleteAllById(List<String> ids);

    void deleteAll();

}
