package com.manage_staff.service;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.manage_staff.dto.request.StaffRequest;
import com.manage_staff.dto.request.StaffUpdateRequest;
import com.manage_staff.dto.response.StaffResponse;

public interface IStaffService {
    List<StaffResponse> findAll();

    List<StaffResponse> findAllById(List<String> ids);

    List<StaffResponse> findAllByNameLike(String name);

    StaffResponse findById(String id);

    StaffResponse save(StaffRequest request, MultipartFile file) throws IOException;

    StaffResponse update(String id, StaffUpdateRequest request, MultipartFile file);

    void deleteById(String id);

    void deleteAllById(List<String> ids);

    void deleteAll();

    Page<StaffResponse> findAllPaging(int currentPage, int pageSize, String sortBy, String orderBy);

    Page<StaffResponse> pagingStaff(
            int currentPage, int pageSize, String type, String value, String sortBy, String orderBy);
}
