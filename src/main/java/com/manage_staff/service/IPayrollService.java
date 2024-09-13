package com.manage_staff.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.manage_staff.dto.request.PayrollRequest;
import com.manage_staff.dto.response.PayrollResponse;

public interface IPayrollService {
    List<PayrollResponse> findAll();

    Page<PayrollResponse> paging(
            String column, String value, int currentPage, int pageSize, String orderBy, String sortBy);

    PayrollResponse findById(String id);

    PayrollResponse save(PayrollRequest request);

    PayrollResponse update(String id, PayrollRequest request);

    void deleteById(String id);

    void deleteAllById(List<String> ids);

    void deleteAll();
}
