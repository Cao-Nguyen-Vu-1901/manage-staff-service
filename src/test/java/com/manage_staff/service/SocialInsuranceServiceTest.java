package com.manage_staff.service;

import com.manage_staff.dto.request.SocialInsuranceRequest;
import com.manage_staff.dto.request.SocialInsuranceUpdateRequest;
import com.manage_staff.entity.SocialInsurance;
import com.manage_staff.entity.Staff;
import com.manage_staff.exception.AppException;
import com.manage_staff.repository.SocialInsuranceRepository;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.manage_staff.repository.StaffRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.Optional;

@SpringBootTest
public class SocialInsuranceServiceTest {

    @MockBean
    private SocialInsuranceRepository socialInsuranceRepository;
    @MockBean
    private StaffRepository staffRepository;

    @Autowired
    private ISocialInsuranceService socialInsuranceService;

    private SocialInsurance socialInsurance;
    private SocialInsurance socialInsuranceUpdate;

    private SocialInsuranceRequest socialInsuranceRequest;
    private SocialInsuranceUpdateRequest socialInsuranceUpdateRequest;

    @BeforeEach
    void initData() {
        socialInsuranceRequest = SocialInsuranceRequest.builder()
                .beginDay(LocalDate.of(2024, 9, 2))
                .expiryDate(LocalDate.of(2025, 9, 2))
                .registrationArea("abc")
                .build();

        socialInsuranceUpdateRequest = SocialInsuranceUpdateRequest.builder()
                .beginDay(LocalDate.of(2024, 9, 2))
                .expiryDate(LocalDate.of(2025, 9, 2))
                .registrationArea("abc")
                .build();

        socialInsurance = SocialInsurance.builder()
                .id("si1")
                .beginDay(LocalDate.of(2024, 9, 2))
                .expiryDate(LocalDate.of(2025, 9, 2))
                .registrationArea("abc")
                .staff(Staff.builder().id("st1").build())
                .build();

        socialInsuranceUpdate = SocialInsurance.builder()
                .id("si1")
                .beginDay(LocalDate.of(2024, 9, 2))
                .expiryDate(LocalDate.of(2025, 9, 2))
                .registrationArea("efg")
                .staff(Staff.builder().id("st1").build())
                .build();
    }

    @Test
    void findById_success(){
        when(socialInsuranceRepository.findById(anyString())).thenReturn(Optional.ofNullable(socialInsurance));
        var response = socialInsuranceService.findById(anyString());
        Assertions.assertThat(response.getId()).isEqualTo("si1");
    }

    @Test
    void findById_error(){
        when(socialInsuranceRepository.findById(anyString())).thenReturn(Optional.ofNullable(null));
        var exception = assertThrows(AppException.class, () -> socialInsuranceService.findById(anyString()));
        Assertions.assertThat(exception.getErrorCode().getCode()).isEqualTo(1210);
    }
    @Test
    void createSI_error_staffNotExist() {

        socialInsuranceRequest.setStaff("st");

        when(staffRepository.findById(anyString())).thenReturn(Optional.empty());

        var exception = assertThrows(AppException.class, () -> socialInsuranceService.save(socialInsuranceRequest));
        Assertions.assertThat(exception.getErrorCode().getCode()).isEqualTo(1211);
    }


    @Test
    void update_success(){
        when(socialInsuranceRepository.findById(anyString())).thenReturn(Optional.ofNullable(socialInsurance));
        when(socialInsuranceRepository.save(any())).thenReturn(socialInsuranceUpdate);

        var response = socialInsuranceService.update(anyString(), socialInsuranceUpdateRequest);
        Assertions.assertThat(response.getId()).isEqualTo("si1");
    }

    @Test
    void update_error_notFoundSI(){
        when(socialInsuranceRepository.findById(anyString())).thenReturn(Optional.ofNullable(null));
        var exception = assertThrows(AppException.class, () -> socialInsuranceService.update(anyString(), socialInsuranceUpdateRequest));
        Assertions.assertThat(exception.getErrorCode().getCode()).isEqualTo(1210);
    }

}
