package com.manage_staff.service;

import com.manage_staff.dto.request.PermissionRequest;
import com.manage_staff.entity.Permission;
import com.manage_staff.exception.AppException;
import com.manage_staff.repository.PermissionRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

@SpringBootTest
public class PermissionServiceTest {

    @Autowired
    private IPermissionService permissionService;

    @MockBean
    private PermissionRepository permissionRepository;

    private PermissionRequest permissionRequest;
    private Permission permission;
    private Permission permissionUpdate;

    @BeforeEach
    void initData(){
        permission = Permission.builder().name("VIEW PERMISSION").description("User can view permission").build();
        permissionRequest = PermissionRequest.builder().name("VIEW PERMISSION").description("User can view permission").build();
        permissionUpdate = Permission.builder().name("UPDATE VIEW PERMISSION").description("User can view permission").build();
    }

    @Test
    void findById_success(){
        when(permissionRepository.findById(anyString())).thenReturn(Optional.of(permission));
        var response = permissionService.findById(anyString());
        Assertions.assertThat(response.getName()).isEqualTo("VIEW PERMISSION");
    }
    @Test
    void findById_error(){
        when(permissionRepository.findById(anyString())).thenReturn(Optional.ofNullable(null));
        var exception = assertThrows(AppException.class, () -> permissionService.findById(anyString()));
        Assertions.assertThat(exception.getErrorCode().getCode()).isEqualTo(1206);
    }

    @Test
    void createPermission_success(){
        when(permissionRepository.existsByName(anyString())).thenReturn(false);
        when(permissionRepository.save(any())).thenReturn(permission);

        var response = permissionService.save(permissionRequest);
        Assertions.assertThat(response.getName()).isEqualTo("VIEW PERMISSION");
    }
    @Test
    void createPermission_error(){
        when(permissionRepository.existsByName(anyString())).thenReturn(true);

        var exception = assertThrows(AppException.class, () -> permissionService.save(permissionRequest));
        Assertions.assertThat(exception.getErrorCode().getCode()).isEqualTo(1105);
    }
    @Test
    void updatePermission_success(){
        when(permissionRepository.findById(anyString())).thenReturn(Optional.of(permission));
        when(permissionRepository.save(any())).thenReturn(permissionUpdate);
        var response = permissionService.update(anyString(),permissionRequest);
        Assertions.assertThat(response.getName()).isEqualTo("UPDATE VIEW PERMISSION");
    }
    @Test
    void updatePermission_error(){
        when(permissionRepository.findById(anyString())).thenReturn(Optional.ofNullable(null));
        var exception = assertThrows(AppException.class, () -> permissionService.update(anyString(),permissionRequest));
        Assertions.assertThat(exception.getErrorCode().getCode()).isEqualTo(1206);
    }

}
