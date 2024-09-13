package com.manage_staff.service;

import com.manage_staff.dto.request.DepartmentRequest;
import com.manage_staff.dto.response.DepartmentResponse;
import com.manage_staff.dto.response.PositionResponse;
import com.manage_staff.entity.Department;
import com.manage_staff.entity.Position;
import com.manage_staff.exception.AppException;
import com.manage_staff.repository.DepartmentRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class DepartmentServiceTest {


    @Autowired
    private IDepartmentService departmentService;

    @MockBean
    private DepartmentRepository departmentRepository;

    private DepartmentResponse departmentResponse;
    private Department department;
    private Department departmentUpdate;
    private DepartmentRequest departmentRequest;

    @BeforeEach
    void initData() {

        departmentRequest = DepartmentRequest.builder()
                .name("abc")
                .description("efg")
                .build();

        department = Department.builder()
                .id("de1")
                .name("abc")
                .description("efg")
                .build();

        departmentUpdate = Department.builder()
                .id("de1")
                .name("cnv")
                .description("cnv")
                .build();


    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void createDepartment_success() {
        // Prepare test data


        // Mock the mapping and repository call

        when(departmentRepository.save(any())).thenReturn(department);

        // Call the service method
        var response = departmentService.save(departmentRequest);

        // Assertions
        Assertions.assertThat(response.getId()).isEqualTo("de1");
        Assertions.assertThat(response.getName()).isEqualTo("abc");
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void updateDepartment_error_notExist() {
        when(departmentRepository.findById("de1")).thenReturn(Optional.ofNullable(null));
        var exception = assertThrows(AppException.class, () -> departmentService.update("de1", departmentRequest));
        Assertions.assertThat(exception.getErrorCode().getCode()).isEqualTo(1203);
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void updateDepartment_success() {
        when(departmentRepository.findById("de1")).thenReturn(Optional.ofNullable(department));
        when(departmentRepository.save(department)).thenReturn(departmentUpdate);

        var response = departmentService.update("de1", departmentRequest);

        Assertions.assertThat(response.getId()).isEqualTo("de1");
        Assertions.assertThat(response.getName()).isEqualTo("cnv");
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void findById_success() {
        when(departmentRepository.findById("de1")).thenReturn(Optional.ofNullable(department));
        var response = departmentService.findById("de1");
        Assertions.assertThat(response.getId()).isEqualTo("de1");
        Assertions.assertThat(response.getName()).isEqualTo("abc");
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void findById_error_notFound() {
        when(departmentRepository.findById(any())).thenReturn(Optional.ofNullable(null));
        var exception = assertThrows(AppException.class, () -> departmentService.findById("de1"));
        Assertions.assertThat(exception.getErrorCode().getCode()).isEqualTo(1203);
    }
}

