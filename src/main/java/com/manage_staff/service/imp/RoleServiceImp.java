package com.manage_staff.service.imp;

import com.manage_staff.dto.request.RoleRequest;
import com.manage_staff.dto.request.RoleUpdateRequest;
import com.manage_staff.dto.response.RoleResponse;
import com.manage_staff.entity.Role;
import com.manage_staff.exception.AppException;
import com.manage_staff.exception.ErrorCode;
import com.manage_staff.mapper.RoleMapper;
import com.manage_staff.repository.PermissionRepository;
import com.manage_staff.repository.RoleRepository;
import com.manage_staff.service.IRoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class RoleServiceImp implements IRoleService {

    RoleRepository roleRepository;
    RoleMapper roleMapper;
    PermissionRepository permissionRepository;


    @Override
    public List<RoleResponse> findAll() {
        return roleRepository.findAll()
                .stream().map(roleMapper::toRoleResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<RoleResponse> findAllById(List<String> ids) {
        return roleRepository.findAllById(ids)
                .stream().map(roleMapper::toRoleResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<RoleResponse> findAllByNameLike(String name) {
        return roleRepository
                .findAllByNameLike("%" + name + "%")
                .stream().map(roleMapper::toRoleResponse)
                .collect(Collectors.toList());
    }

    @Override
    public RoleResponse findById(String id) {
        return roleMapper
                .toRoleResponse(roleRepository.findById(id)
                        .orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_EXISTED)));
    }

    @Override
    public RoleResponse save(RoleRequest request) {
        List<Role> roles= roleRepository.findAllByName(request.getName());
        if(!roles.isEmpty()){
            throw new AppException(ErrorCode.ROLE_EXISTED);
        }else {

            Role role = roleMapper.toRole(request);
            var permissionIds = request.getPermissions();
            var permission = permissionRepository.findAllById(permissionIds);
            role.setPermissions(new HashSet<>(permission));
            return roleMapper.toRoleResponse(roleRepository.save(role));
        }
    }

    @Override
    public RoleResponse update(String id, RoleUpdateRequest request) {
        Role role = roleRepository.findById(id).orElseThrow( () -> new AppException(ErrorCode.ROLE_NOT_EXISTED));
        if(request.getPermissions() != null){
            var permissions = permissionRepository.findAllById(request.getPermissions());
            role.setPermissions(new HashSet<>(permissions));
        }
        roleMapper.updateRole(role,request);
        return roleMapper.toRoleResponse(roleRepository.save(role));
    }

    @Override
    public void deleteById(String id) {
        roleRepository.deleteById(id);
    }

    @Override
    public void deleteAllById(List<String> ids) {
        roleRepository.deleteAllById(ids);
    }

    @Override
    public void deleteAll() {
        roleRepository.deleteAll();
    }
}
