package com.manage_staff.service;


import com.manage_staff.dto.request.DepartmentRequest;
import com.manage_staff.dto.request.LeaveDayRequest;
import com.manage_staff.dto.response.DepartmentResponse;
import com.manage_staff.dto.response.LeaveDayResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ILeaveDayService {
    List<LeaveDayResponse> findAll();
    List<LeaveDayResponse> findAllById( List<String> ids);
    List<LeaveDayResponse> findAllByNameLike(String name);

    Page<LeaveDayResponse> paging(String column, String value,
                                  int currentPage, int pageSize,
                                  String orderBy, String sortBy);
    LeaveDayResponse findById(String id);

    LeaveDayResponse save(LeaveDayRequest request);

    LeaveDayResponse update(String id, LeaveDayRequest request);

    void deleteById(String id);

    void deleteAllById(List<String> ids);

    void deleteAll();

}
