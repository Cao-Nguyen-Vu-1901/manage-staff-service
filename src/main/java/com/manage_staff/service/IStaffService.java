package com.manage_staff.service;


import com.manage_staff.dto.request.StaffRequest;
import com.manage_staff.dto.request.StaffUpdateRequest;
import com.manage_staff.dto.response.LeaveDayResponse;
import com.manage_staff.dto.response.StaffResponse;
import com.manage_staff.entity.Staff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface IStaffService {
    List<StaffResponse> findAll();

    List<StaffResponse> findAllById(List<String> ids);

    List<StaffResponse> findAllByNameLike(String name);

    StaffResponse findById(String id);

    StaffResponse save(StaffRequest request);

    StaffResponse update(String id, StaffUpdateRequest request);

    void deleteById(String id);

    void deleteAllById(List<String> ids);

    void deleteAll();

    Page<StaffResponse> findAllPaging(int currentPage, int pageSize, String sortBy, String orderBy);

    Page<StaffResponse> findAllByDobPaging(int currentPage, int pageSize, String type, String value, String sortBy, String orderBy);


}
