package com.manage_staff.service;


import com.manage_staff.dto.request.PayrollRequest;
import com.manage_staff.dto.response.LeaveDayResponse;
import com.manage_staff.dto.response.PayrollResponse;

import java.util.List;

public interface IPayrollService {
    List<PayrollResponse> findAll();
    PayrollResponse findById(String id);

    PayrollResponse save(PayrollRequest request);

    PayrollResponse update(String id, PayrollRequest request);

    void deleteById(String id);

    void deleteAllById(List<String> ids);

    void deleteAll();
}
