package com.manage_staff.service.imp;

import com.manage_staff.dto.request.PermissionRequest;
import com.manage_staff.dto.response.PermissionResponse;
import com.manage_staff.entity.Permission;
import com.manage_staff.exception.AppException;
import com.manage_staff.exception.ErrorCode;
import com.manage_staff.mapper.PermissionMapper;
import com.manage_staff.repository.PermissionRepository;
import com.manage_staff.service.IPermissionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class PermissionServiceImp implements IPermissionService {

    PermissionRepository permissionRepository;
    PermissionMapper permissionMapper;

    @Override
    public List<PermissionResponse> findAll() {
        return permissionRepository.findAll()
                .stream().map(permissionMapper::toPermissionResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<PermissionResponse> findAllById(List<String> ids) {
        return permissionRepository.findAllById(ids)
                .stream().map(permissionMapper::toPermissionResponse).collect(Collectors.toList());
    }

    @Override
    public List<PermissionResponse> findAllByNameLike(String name) {
        return permissionRepository.findAllByNameLike("%" + name + "%")
                .stream().map(permissionMapper::toPermissionResponse)
                .collect(Collectors.toList());
    }

    @Override
    public PermissionResponse findById(String id) {
        return permissionMapper.toPermissionResponse(permissionRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.PERMISSION_NOT_EXISTED)));
    }

    @Override
    public PermissionResponse save(PermissionRequest request) {
        if(!permissionRepository.findAllByName(request.getName()).isEmpty()){
            throw new AppException(ErrorCode.PERMISSION_EXISTED);
        }
        Permission permission = permissionMapper.toPermission(request);
        return permissionMapper.toPermissionResponse(permissionRepository.save(permission));
    }

    @Override
    public PermissionResponse update(String id, PermissionRequest request) {
        Permission permission = permissionRepository.findById(id).orElseThrow( ()-> new AppException(ErrorCode.PERMISSION_NOT_EXISTED));
        permissionMapper.updatePermission(permission,request);
        return permissionMapper.toPermissionResponse(permissionRepository.save(permission));
    }

    @Override
    public void deleteById(String id) {
        permissionRepository.deleteById(id);
    }

    @Override
    public void deleteAllById(List<String> ids) {
        permissionRepository.deleteAllById(ids);
    }

    @Override
    public void deleteAll() {
        permissionRepository.deleteAll();
    }
}
