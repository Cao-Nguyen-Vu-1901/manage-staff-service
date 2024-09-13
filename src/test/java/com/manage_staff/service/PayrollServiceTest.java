package com.manage_staff.service;

import com.manage_staff.dto.request.PayrollRequest;
import com.manage_staff.dto.response.PayrollResponse;
import com.manage_staff.entity.Payroll;
import com.manage_staff.exception.AppException;
import com.manage_staff.repository.PayrollRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.Optional;

@SpringBootTest
public class PayrollServiceTest {

    @Autowired
    private IPayrollService payrollService;

    @MockBean
    private PayrollRepository payrollRepository;

    private PayrollRequest payrollRequest;
    private Payroll payroll;
    private Payroll payrollUpdate;

    @BeforeEach
    void initData(){

        payrollRequest = PayrollRequest.builder()
                .coefficient(2)
                .basicSalary(new BigDecimal("1.5")).build();

        payroll = Payroll.builder()
                .id("p1")
                .coefficient(2)
                .basicSalary(new BigDecimal("1.5")).build();

        payrollUpdate = Payroll.builder()
                .id("p1")
                .coefficient(5)
                .basicSalary(new BigDecimal("2.5")).build();
    }

    @Test
    void findById_success(){
        when(payrollRepository.findById("p1")).thenReturn(Optional.of(payroll));
        var response = payrollService.findById("p1");
        Assertions.assertThat(response.getId()).isEqualTo("p1");
        Assertions.assertThat(response.getCoefficient()).isEqualTo(2);
    }

    @Test
    void findById(){
        when(payrollRepository.findById(anyString())).thenReturn(Optional.ofNullable(null));
        var exception = assertThrows(AppException.class, () -> payrollService.findById(anyString()));
        Assertions.assertThat(exception.getErrorCode().getCode()).isEqualTo(1205);
    }

    @Test
    void createPayroll_success(){
        when(payrollRepository.save(any())).thenReturn(payroll);
        var response = payrollService.save(payrollRequest);
        Assertions.assertThat(response.getId()).isEqualTo("p1");
        Assertions.assertThat(response.getCoefficient()).isEqualTo(2);

    }

    @Test
    void updatePayroll_error_notFound(){
        when(payrollRepository.findById("p1")).thenReturn(Optional.ofNullable(null));

        var exception = assertThrows(AppException.class, ()-> payrollService.findById("p1"));
        Assertions.assertThat(exception.getErrorCode().getCode()).isEqualTo(1205);
    }
    @Test
    void updatePayroll_success(){

        when(payrollRepository.findById("p1")).thenReturn(Optional.of(payroll));

        when( payrollRepository.save(any())).thenReturn(payrollUpdate);

        var response = payrollService.save(payrollRequest);
        Assertions.assertThat(response.getId()).isEqualTo("p1");
        Assertions.assertThat(response.getCoefficient()).isEqualTo(5);
    }

}
