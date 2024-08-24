package com.manage_staff.mapper;

import com.manage_staff.dto.request.PayrollRequest;
import com.manage_staff.dto.response.PayrollResponse;
import com.manage_staff.dto.response.PositionResponse;
import com.manage_staff.entity.Payroll;
import com.manage_staff.entity.Position;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface PayrollMapper {
    Payroll toPayroll (PayrollRequest request);

    @Mapping(target = "positions",ignore = true)
    PayrollResponse toPayrollResponse (Payroll payroll);

    void updatePayroll(@MappingTarget Payroll payroll, PayrollRequest request);

}
