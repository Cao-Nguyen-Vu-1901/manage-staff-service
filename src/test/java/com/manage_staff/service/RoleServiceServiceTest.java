package com.manage_staff.service;

import com.manage_staff.dto.request.RoleRequest;
import com.manage_staff.dto.request.RoleUpdateRequest;
import com.manage_staff.entity.Role;
import com.manage_staff.exception.AppException;
import com.manage_staff.repository.RoleRepository;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.HashSet;
import java.util.Optional;

@SpringBootTest
public class RoleServiceServiceTest {

    @MockBean
    private RoleRepository roleRepository;

    @Autowired
    private IRoleService roleService;

    private RoleRequest roleRequest;
    private RoleUpdateRequest roleUpdateRequest;
    private Role role;
    private Role roleUpdate;

    @BeforeEach
    void initData(){
        roleRequest = RoleRequest.builder()
                .name("VIEWER")
                .description("You can view the website")
                .permissions(new HashSet<>()).build();

        roleUpdateRequest = RoleUpdateRequest.builder()
                .description("You can not view the website")
                .permissions(new HashSet<>())
                .build();

        role = Role.builder()
                .name("VIEWER")
                .description("You can view the website")
                .permissions(new HashSet<>())
                .build();

        roleUpdate = Role.builder()
                .name("VIEWER")
                .description("You can not view the website")
                .permissions(new HashSet<>())
                .build();
    }

    @Test
    void findById_success(){
        when(roleRepository.findById(anyString())).thenReturn(Optional.ofNullable(role));
        var response = roleService.findById(anyString());
        Assertions.assertThat(response.getName()).isEqualTo("VIEWER");
        Assertions.assertThat(response.getDescription()).isEqualTo("You can view the website");

    }

    @Test
    void findById_error_notFound(){
        when(roleRepository.findById(anyString())).thenReturn(Optional.ofNullable(null ));
        var exception = assertThrows(AppException.class, () -> roleService.findById(anyString()));
        Assertions.assertThat(exception.getErrorCode().getCode()).isEqualTo(1209);
    }

    @Test
    void createRole_success(){
        when(roleRepository.existsByName(anyString())).thenReturn(false);
        when(roleRepository.save(any())).thenReturn(role);

        var response = roleService.save(roleRequest);
        Assertions.assertThat(response.getName()).isEqualTo("VIEWER");
        Assertions.assertThat(response.getDescription()).isEqualTo("You can view the website");

    }

    @Test
    void createRole_error_existed(){
        when(roleRepository.existsByName(anyString())).thenReturn(true);
        var exception = assertThrows(AppException.class, () -> roleService.save(roleRequest));

        Assertions.assertThat(exception.getErrorCode().getCode()).isEqualTo(1108);
    }

    @Test
    void updateRole_success(){
        when(roleRepository.findById(anyString())).thenReturn(Optional.ofNullable(role));
        when(roleRepository.save(any())).thenReturn(roleUpdate);

        var response = roleService.update(anyString(), roleUpdateRequest);
        Assertions.assertThat(response.getName()).isEqualTo("VIEWER");
        Assertions.assertThat(response.getDescription()).isEqualTo("You can not view the website");
    }
    @Test
    void updateRole_error_notFoundRole(){
        when(roleRepository.findById(anyString())).thenReturn(Optional.ofNullable(null));

        var exception = assertThrows(AppException.class, () -> roleService.update(anyString(), roleUpdateRequest));
        Assertions.assertThat(exception.getErrorCode().getCode()).isEqualTo(1209);
    }

}
