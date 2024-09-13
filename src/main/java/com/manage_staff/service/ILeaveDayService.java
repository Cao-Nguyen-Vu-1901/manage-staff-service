package com.manage_staff.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.manage_staff.dto.request.LeaveDayRequest;
import com.manage_staff.dto.response.LeaveDayResponse;

public interface ILeaveDayService {
    List<LeaveDayResponse> findAll();

    List<LeaveDayResponse> findAllById(List<String> ids);

    List<LeaveDayResponse> findAllByNameLike(String name);

    Page<LeaveDayResponse> paging(
            String column, String value, int currentPage, int pageSize, String orderBy, String sortBy);

    LeaveDayResponse findById(String id);

    LeaveDayResponse save(LeaveDayRequest request);

    LeaveDayResponse update(String id, LeaveDayRequest request);

    void deleteById(String id);

    void deleteAllById(List<String> ids);

    void deleteAll();
}
