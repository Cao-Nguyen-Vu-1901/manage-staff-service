package com.manage_staff.service.imp;
import com.manage_staff.dao.StaffDAO;
import com.manage_staff.constant.StringConstant;
 import com.manage_staff.dto.request.StaffRequest;
import com.manage_staff.dto.request.StaffUpdateRequest;
import com.manage_staff.dto.response.StaffResponse;
import com.manage_staff.entity.Role;
import com.manage_staff.entity.Staff;
import com.manage_staff.exception.AppException;
import com.manage_staff.exception.ErrorCode;
import com.manage_staff.mapper.PositionMapper;
import com.manage_staff.mapper.StaffMapper;
import com.manage_staff.repository.RoleRepository;
import com.manage_staff.repository.StaffRepository;
import com.manage_staff.service.IStaffService;
import com.manage_staff.util.ProcessImage;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StaffServiceImp implements IStaffService {

    StaffRepository staffRepository;
    StaffMapper staffMapper;
    RoleRepository roleRepository;
    PositionMapper positionMapper;
    PasswordEncoder passwordEncoder;
    StaffDAO staffDAO;

    @Override
    public List<StaffResponse> findAll() {
        return staffRepository.findAll().stream().map(staffMapper::toStaffResponse).collect(Collectors.toList());
    }

    @Override
    public List<StaffResponse> findAllById(List<String> ids) {
        return staffRepository.findAllById(ids)
                .stream().map(staffMapper::toStaffResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<StaffResponse> findAllByNameLike(String name) {
        return staffRepository.findAllByNameLike("%" + name + "%")
                .stream().map(staffMapper::toStaffResponse)
                .collect(Collectors.toList());
    }


    @PostAuthorize("returnObject.username == authentication.name")
    @Override
    public StaffResponse findById(String id) {
        return staffMapper.toStaffResponse(
                staffRepository.findById(id)
                        .orElseThrow(() -> new AppException(ErrorCode.STAFF_NOT_EXISTED)));
    }

    @Override
    public StaffResponse save(StaffRequest request, MultipartFile file) throws IOException {

        if (staffRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new AppException(ErrorCode.STAFF_EXISTED);
        }

        Staff staff = staffMapper.toStaff(request);



        if (request.getRoles() != null) {
            var roleIds = request.getRoles();
            var roles = roleRepository.findAllById(roleIds);
            staff.setRoles(new HashSet<>(roles));
        }
        staff.setPassword(passwordEncoder.encode(request.getPassword()));

        var roles = new HashSet<Role>();
        roles.add(roleRepository.findById(StringConstant.ROLE_USER)
                .orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_EXISTED)));
        staff.setRoles(roles);

        return staffMapper.toStaffResponse(staffRepository.save(staff));
    }

    @Override
    public StaffResponse update(String id, StaffUpdateRequest request, MultipartFile file) {
        Staff staff = staffRepository.findById(id)
                .orElseThrow( () -> new AppException(ErrorCode.STAFF_NOT_EXISTED));
        staffMapper.updateStaff(staff,request);

        if(request.getRoles() != null){

            var roleIds = request.getRoles();
            var roles = roleRepository.findAllById(roleIds);
            staff.setRoles(new HashSet<>(roles));
        }


        try {
            if(file != null){
                String imageName = ProcessImage.upload(file, "staff/");
                staff.setImage(imageName);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return positionMapper.staffToStaffResponse(staffRepository.save(staff));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public void deleteById(String id) {
        staffRepository.deleteById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public void deleteAllById(List<String> ids) {
        staffRepository.deleteAllById(ids);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public void deleteAll() {
        staffRepository.deleteAll();
    }

    @Override
    public Page<StaffResponse> findAllPaging(int currentPage, int pageSize, String sortBy, String orderBy) {
        Pageable pageable = null;
        if (sortBy != null) {
            pageable = PageRequest.of(currentPage - 1, pageSize,
                    Sort.by(orderBy != null && orderBy.equals("DESC")
                            ? Sort.Direction.DESC
                            : Sort.Direction.ASC, sortBy));
        } else {
            pageable = PageRequest.of(currentPage - 1, pageSize);

        }
        return staffRepository.findAll(pageable).map(positionMapper::staffToStaffResponse);
    }

    @Override
    public Page<StaffResponse> pagingStaff(int currentPage, int pageSize, String type, String value, String sortBy, String orderBy) {

        return staffDAO.paging(type, value, pageSize, currentPage, sortBy, orderBy).map(positionMapper::staffToStaffResponse);
    }

}
