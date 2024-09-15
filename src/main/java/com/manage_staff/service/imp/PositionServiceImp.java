package com.manage_staff.service.imp;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.manage_staff.dto.request.PositionRequest;
import com.manage_staff.dto.response.PositionResponse;
import com.manage_staff.entity.Position;
import com.manage_staff.exception.AppException;
import com.manage_staff.exception.ErrorCode;
import com.manage_staff.mapper.DepartmentMapper;
import com.manage_staff.mapper.PositionMapper;
import com.manage_staff.repository.DepartmentRepository;
import com.manage_staff.repository.PayrollRepository;
import com.manage_staff.repository.PositionRepository;
import com.manage_staff.repository.StaffRepository;
import com.manage_staff.service.IPositionService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class PositionServiceImp implements IPositionService {

    PositionMapper positionMapper;
    PositionRepository positionRepository;
    StaffRepository staffRepository;
    PayrollRepository payrollRepository;
    DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;

    @Override
    public List<PositionResponse> findAll() {
        return positionRepository.findAll().stream()
                .map(positionMapper::toPositionResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<PositionResponse> findAllById(List<String> ids) {
        return positionRepository.findAllById(ids).stream()
                .map(positionMapper::toPositionResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<PositionResponse> findAllByNameLike(String name) {
        return positionRepository.findAllByNameLike("%" + name + "%").stream()
                .map(positionMapper::toPositionResponse)
                .collect(Collectors.toList());
    }

    @Override
    public PositionResponse findById(String id) {
        return positionMapper.toPositionResponse(
                positionRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.POSITION_NOT_EXISTED)));
    }

    @Override
    public PositionResponse save(PositionRequest request) {
        if (positionRepository.existsByName(request.getName())) {
            throw new AppException(ErrorCode.POSITION_EXISTED);
        } else {
            Position position = positionMapper.toPosition(request);

            if (request.getPayroll() != null) {
                String payrollId = request.getPayroll();
                var payroll = payrollRepository
                        .findById(payrollId)
                        .orElseThrow(() -> new AppException(ErrorCode.PAYROLL_NOT_EXISTED));
                position.setPayroll(payroll);
            }
            if (request.getDepartment() != null) {
                String departmentId = request.getDepartment();
                var department = departmentRepository
                        .findById(departmentId)
                        .orElseThrow(() -> new AppException(ErrorCode.DEPARTMENT_NOT_EXISTED));
                position.setDepartment(department);
            }
            return positionMapper.toPositionResponse(positionRepository.save(position));
        }
    }

    @Override
    public PositionResponse update(String id, PositionRequest request) {
        Position position =
                positionRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.POSITION_NOT_EXISTED));
        if (request.getPayroll() != null) {
            var payrolls = payrollRepository
                    .findById(request.getPayroll())
                    .orElseThrow(() -> new AppException(ErrorCode.PAYROLL_NOT_EXISTED));
            position.setPayroll(payrolls);
        }
        if (request.getDepartment() != null) {
            var department = departmentRepository
                    .findById(request.getDepartment())
                    .orElseThrow(() -> new AppException(ErrorCode.DEPARTMENT_NOT_EXISTED));
            position.setDepartment(department);
        }
        positionMapper.updatePosition(position, request);
        return departmentMapper.positionToPositionResponse(positionRepository.save(position));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public void deleteById(String id) {
        positionRepository.deleteById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public void deleteAllById(List<String> ids) {
        positionRepository.deleteAllById(ids);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public void deleteAll() {
        positionRepository.deleteAll();
    }
}
