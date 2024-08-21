package com.manage_staff.mapper;

import com.manage_staff.dto.request.PayrollRequest;
import com.manage_staff.dto.response.PayrollResponse;
import com.manage_staff.entity.Payroll;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PayrollMapper {
    Payroll toPayroll (PayrollRequest request);
    PayrollResponse toPayrollResponse (Payroll payroll);
}
