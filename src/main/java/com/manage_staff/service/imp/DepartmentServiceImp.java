package com.manage_staff.service.imp;

import com.manage_staff.dto.request.DepartmentRequest;
import com.manage_staff.dto.response.DepartmentResponse;
import com.manage_staff.entity.Department;
import com.manage_staff.exception.AppException;
import com.manage_staff.exception.ErrorCode;
import com.manage_staff.mapper.DepartmentMapper;
import com.manage_staff.repository.DepartmentRepository;
import com.manage_staff.repository.PositionRepository;
import com.manage_staff.service.IDepartmentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class DepartmentServiceImp implements IDepartmentService {

    DepartmentRepository departmentRepository;
    DepartmentMapper departmentMapper;
    PositionRepository positionRepository;

    @Override
    public List<DepartmentResponse> findAll() {
        return departmentRepository.findAll()
                .stream().map(departmentMapper::toDepartmentResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<DepartmentResponse> findAllByNameLike(String name) {
        return departmentRepository.findAllByNameLike("%" + name + "%")
                .stream().map(departmentMapper::toDepartmentResponse)
                .collect(Collectors.toList());
    }

    @Override
    public DepartmentResponse findById(String id) {
        return departmentMapper
                .toDepartmentResponse(departmentRepository.findById(id).orElseThrow(
                () -> new AppException((ErrorCode.DEPARTMENT_NOT_EXISTED))));
    }

    @Override
    public DepartmentResponse save(DepartmentRequest request) {
        Department department = departmentMapper.toDepartment(request);
        return departmentMapper.toDepartmentResponse(departmentRepository.save(department));
    }

    @Override
    public void deleteById(String id) {
        departmentRepository.deleteById(id);
    }

    @Override
    public void deleteAllById(List<String> ids) {
        departmentRepository.deleteAllById(ids);
    }

    @Override
    public void deleteAll() {
        departmentRepository.deleteAll();
    }
}
