package com.manage_staff.service.imp;

import com.manage_staff.dao.DepartmentDAO;
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
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class DepartmentServiceImp implements IDepartmentService {

    DepartmentRepository departmentRepository;
    DepartmentMapper departmentMapper;
    DepartmentDAO departmentDAO;


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
    public Page<DepartmentResponse> paging(String column, String value, int currentPage, int pageSize, String orderBy, String sortBy) {
        return departmentDAO.paging(column,value,currentPage,pageSize,orderBy,sortBy)
                .map(departmentMapper::toDepartmentResponse);
    }

    @Override
    public DepartmentResponse findById(String id) {
        return departmentMapper
                .toDepartmentResponse(departmentRepository.findById(id).orElseThrow(
                () -> new AppException((ErrorCode.DEPARTMENT_NOT_EXISTED))));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public DepartmentResponse save(DepartmentRequest request) {
        Department department = departmentMapper.toDepartment(request);
        return departmentMapper.toDepartmentResponse(departmentRepository.save(department));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public DepartmentResponse update(String id, DepartmentRequest request) {

        Department department = departmentRepository.findById(id)
                .orElseThrow( () -> new AppException(ErrorCode.DEPARTMENT_NOT_EXISTED));

        departmentMapper.updateDepartment(department,request);

        return departmentMapper.toDepartmentResponse(departmentRepository.save(department));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public void deleteById(String id) {
        departmentRepository.deleteById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public void deleteAllById(List<String> ids) {
        departmentRepository.deleteAllById(ids);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public void deleteAll() {
        departmentRepository.deleteAll();
    }
}
