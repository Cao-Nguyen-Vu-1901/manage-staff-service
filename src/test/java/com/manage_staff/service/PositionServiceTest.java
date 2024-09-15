package com.manage_staff.service;

import com.manage_staff.dto.request.PositionRequest;
import com.manage_staff.entity.Department;
import com.manage_staff.entity.Payroll;
import com.manage_staff.entity.Position;
import com.manage_staff.exception.AppException;
import com.manage_staff.repository.DepartmentRepository;
import com.manage_staff.repository.PayrollRepository;
import com.manage_staff.repository.PositionRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.matches;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

@SpringBootTest
public class PositionServiceTest {

    @MockBean
    private PositionRepository positionRepository;
    @MockBean
    private PayrollRepository payrollRepository;
    @MockBean
    private DepartmentRepository departmentRepository;

    @Autowired
    private IPositionService positionService;

    private PositionRequest positionRequest;
    private Position position;

    @BeforeEach
    void initData(){
        positionRequest = PositionRequest.builder()
                .name("abc")
                .payroll("p1")
                .department("de1").build();

        position = Position.builder()
                .id("po1")
                .name("abc")
                .payroll(Payroll.builder().id("pa1").build())
                .department(Department.builder().id("de1").build()).build();

    }

    @Test
    void findById_success(){
        when(positionRepository.findById(anyString())).thenReturn(Optional.of(position));

        var response = positionService.findById(anyString());
        Assertions.assertThat(response.getId()).isEqualTo("po1");
    }

    @Test
    void findById_error_notFound(){
        when(positionRepository.findById(anyString())).thenReturn(Optional.ofNullable(null));

        var exception = assertThrows(AppException.class, () -> positionService.findById(anyString()));
        Assertions.assertThat(exception.getErrorCode().getCode()).isEqualTo(1207);
    }

    @Test
    void createPosition_success(){

        Department department = Department.builder().id("de1").build();
        Payroll payroll = Payroll.builder().id("pa1").build();

        when(positionRepository.existsByName(anyString())).thenReturn(false);
        when(payrollRepository.findById(anyString())).thenReturn(Optional.ofNullable(payroll));
        when(departmentRepository.findById(anyString())).thenReturn(Optional.ofNullable(department));
        when(positionRepository.save(any())).thenReturn(position);

        var response = positionService.save(positionRequest);
        Assertions.assertThat(response.getId()).isEqualTo("po1");
        Assertions.assertThat(response.getName()).isEqualTo("abc");
    }

    @Test
    void createPosition_error_exist_position(){

        when(positionRepository.existsByName(anyString())).thenReturn(true);

        var exception = assertThrows(AppException.class, () -> positionService.save(positionRequest));

        Assertions.assertThat(exception.getErrorCode().getCode()).isEqualTo(1106);


    }
    @Test
    void createPosition_error_notExist_Payroll(){

        when(positionRepository.existsByName(anyString())).thenReturn(false);
        when(payrollRepository.findById(anyString())).thenReturn(Optional.ofNullable(null));

        var exception = assertThrows(AppException.class, () -> positionService.save(positionRequest));

        Assertions.assertThat(exception.getErrorCode().getCode()).isEqualTo(1205);


    }

    @Test
    void createPosition_error_notExist_Department(){
        Payroll payroll = Payroll.builder().id("pa1").build();

        when(positionRepository.existsByName(anyString())).thenReturn(false);
        when(payrollRepository.findById(anyString())).thenReturn(Optional.ofNullable(payroll));
        when(departmentRepository.findById(anyString())).thenReturn(Optional.ofNullable(null));

        var exception = assertThrows(AppException.class, () -> positionService.save(positionRequest));

        Assertions.assertThat(exception.getErrorCode().getCode()).isEqualTo(1203);

    }


    @Test
    void updatePosition_success(){

        Department department = Department.builder().id("de1").build();
        Payroll payroll = Payroll.builder().id("pa1").build();

        when(positionRepository.findById(anyString())).thenReturn(Optional.ofNullable(position));
        when(payrollRepository.findById(anyString())).thenReturn(Optional.ofNullable(payroll));
        when(departmentRepository.findById(anyString())).thenReturn(Optional.ofNullable(department));
        when(positionRepository.save(any())).thenReturn(position);

        var response = positionService.update(anyString(),positionRequest);
        Assertions.assertThat(response.getId()).isEqualTo("po1");
        Assertions.assertThat(response.getName()).isEqualTo("abc");
    }

    @Test
    void updatePosition_error_notFoundPosition(){
        when(positionRepository.findById(anyString())).thenReturn(Optional.ofNullable(null));
        var exception = assertThrows(AppException.class, () -> positionService.findById(anyString()));
        Assertions.assertThat(exception.getErrorCode().getCode()).isEqualTo(1207);

    }
    
    @Test
    void updatePosition_notFoundPayroll(){
        when(positionRepository.findById(anyString())).thenReturn(Optional.of(position));
        when(payrollRepository.findById(anyString())).thenReturn(Optional.ofNullable(null));
        var exception = assertThrows(AppException.class, () -> positionService.update(anyString(),positionRequest));
        Assertions.assertThat(exception.getErrorCode().getCode()).isEqualTo(1205);
    }

    @Test
    void updatePosition_notFoundDepartment(){
        Payroll payroll = Payroll.builder().id("pa1").build();
        when(positionRepository.findById(anyString())).thenReturn(Optional.of(position));
        when(payrollRepository.findById(anyString())).thenReturn(Optional.of(payroll));
        when(departmentRepository.findById(anyString())).thenReturn(Optional.ofNullable(null));
        var exception = assertThrows(AppException.class,() -> positionService.update(anyString(), positionRequest));
        Assertions.assertThat(exception.getErrorCode().getCode()).isEqualTo(1203);
    }

}
