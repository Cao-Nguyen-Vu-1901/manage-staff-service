package com.manage_staff.service;


import com.manage_staff.dto.request.PayrollRequest;
import com.manage_staff.dto.response.LeaveDayResponse;
import com.manage_staff.dto.response.PayrollResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IPayrollService {
    List<PayrollResponse> findAll();

    Page<PayrollResponse> paging(String column, String value,
                                 int currentPage, int pageSize,
                                 String orderBy, String sortBy);
    PayrollResponse findById(String id);

    PayrollResponse save(PayrollRequest request);

    PayrollResponse update(String id, PayrollRequest request);

    void deleteById(String id);

    void deleteAllById(List<String> ids);

    void deleteAll();
}
