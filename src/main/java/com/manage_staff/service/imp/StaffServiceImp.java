package com.manage_staff.service.imp;

import com.manage_staff.dto.request.StaffRequest;
import com.manage_staff.dto.response.StaffResponse;
import com.manage_staff.entity.Staff;
import com.manage_staff.exception.AppException;
import com.manage_staff.exception.ErrorCode;
import com.manage_staff.mapper.StaffMapper;
import com.manage_staff.repository.StaffRepository;
import com.manage_staff.service.IStaffService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class StaffServiceImp implements IStaffService {

    StaffRepository staffRepository;
    StaffMapper staffMapper;

    @Override
    public List<StaffResponse> findAll() {
        return staffRepository.findAll().stream().map(staffMapper::toStaffResponse).collect(Collectors.toList());
    }

    @Override
    public List<StaffResponse> findAllByNameLike(String name) {
        return staffRepository.findAllByNameLike("%" + name + "%")
                .stream().map(staffMapper::toStaffResponse)
                .collect(Collectors.toList());
    }

    @Override
    public StaffResponse findById(String id) {
        return staffMapper.toStaffResponse(
                staffRepository.findById(id)
                        .orElseThrow( () -> new AppException(ErrorCode.STAFF_NOT_EXISTED)));
    }

    @Override
    public StaffResponse save(StaffRequest request) {
        Staff staff = staffMapper.toStaff(request);
        return staffMapper.toStaffResponse(staffRepository.save(staff));
    }

    @Override
    public void deleteById(String id) {
        staffRepository.findById(id);
    }

    @Override
    public void deleteAllById(List<String> ids) {
        staffRepository.deleteAllById(ids);
    }

    @Override
    public void deleteAll() {
        staffRepository.deleteAll();
    }
}
