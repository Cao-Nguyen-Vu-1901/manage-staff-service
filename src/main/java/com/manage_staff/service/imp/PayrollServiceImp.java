package com.manage_staff.service.imp;

import com.manage_staff.dto.request.PayrollRequest;
import com.manage_staff.dto.response.PayrollResponse;
import com.manage_staff.entity.Payroll;
import com.manage_staff.exception.AppException;
import com.manage_staff.exception.ErrorCode;
import com.manage_staff.mapper.PayrollMapper;
import com.manage_staff.repository.PayrollRepository;
import com.manage_staff.service.IPayrollService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class PayrollServiceImp implements IPayrollService {

    PayrollRepository payrollRepository;
    PayrollMapper payrollMapper;
    @Override
    public List<PayrollResponse> findAll() {
        return payrollRepository.findAll()
                .stream().map(payrollMapper::toPayrollResponse)
                .collect(Collectors.toList());
    }

    @Override
    public PayrollResponse findById(String id) {
        return payrollMapper
                .toPayrollResponse(payrollRepository.findById(id)
                        .orElseThrow(() -> new AppException(ErrorCode.PAYROLL_NOT_EXISTED)));
    }

    @Override
    public PayrollResponse save(PayrollRequest request) {
        Payroll payroll = payrollMapper.toPayroll(request);
        return payrollMapper.toPayrollResponse(payrollRepository.save(payroll));
    }

    @Override
    public PayrollResponse update(String id, PayrollRequest request) {
        Payroll payroll = payrollRepository.findById(id)
                .orElseThrow( ()-> new AppException(ErrorCode.PAYROLL_NOT_EXISTED));
        payrollMapper.updatePayroll(payroll,request);
        return payrollMapper.toPayrollResponse(payrollRepository.save(payroll));
    }

    @Override
    public void deleteById(String id) {
        payrollRepository.deleteById(id);
    }

    @Override
    public void deleteAllById(List<String> ids) {
        payrollRepository.deleteAllById(ids);
    }

    @Override
    public void deleteAll() {
        payrollRepository.deleteAll();
    }
}
