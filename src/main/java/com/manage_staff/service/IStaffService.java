package com.manage_staff.service;


import com.manage_staff.dto.request.StaffRequest;
import com.manage_staff.dto.response.LeaveDayResponse;
import com.manage_staff.dto.response.StaffResponse;

import java.util.List;

public interface IStaffService {
    List<StaffResponse> findAll();

    List<StaffResponse> findAllById( List<String> ids);


    List<StaffResponse> findAllByNameLike(String name);
    StaffResponse findById(String id);

    StaffResponse save(StaffRequest request);

    void deleteById(String id);

    void deleteAllById(List<String> ids);

    void deleteAll();
}
