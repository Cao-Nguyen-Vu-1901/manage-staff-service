package com.manage_staff.service;

import com.manage_staff.dto.request.BenefitRequest;
import com.manage_staff.dto.response.BenefitResponse;
import com.manage_staff.entity.Benefit;
import com.manage_staff.exception.AppException;
import com.manage_staff.repository.BenefitRepository;

import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithSecurityContext;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;

import java.math.BigDecimal;
import java.util.Optional;

@SpringBootTest
//@TestPropertySource("/test.properties")
public class BenefitServiceTest {
    @Autowired
    private IBenefitService benefitService;

    @MockBean
    private BenefitRepository benefitRepository;

    private Benefit benefit;
    private BenefitRequest benefitRequest;
    private BenefitRequest benefitUpdateRequest ;
    private Benefit benefitUpdate  ;

    @BeforeEach
    void initData(){
        benefitRequest = BenefitRequest.builder()
                .content("Tiền thưởng cho ngày nghỉ lễ 2/9 hàng năm")
                .effectiveDate(LocalDate.now())
                .money(new BigDecimal("123"))
                .name("Thưởng 2/9")
                .build();
        benefit = Benefit.builder()
                .id("dfbgjrtg890")
                .content("Tiền thưởng cho ngày nghỉ lễ 2/9 hàng năm")
                .effectiveDate(LocalDate.now())
                .money(new BigDecimal("123"))
                .name("Thưởng 2/9")
                .build();

        benefitUpdateRequest = BenefitRequest.builder()
                .content("Tiền thưởng cho ngày nghỉ lễ 30/4 hàng năm")
                .effectiveDate(LocalDate.now())
                .money(new BigDecimal("456"))
                .name("Thưởng 30/4")
                .build();
        benefitUpdate = Benefit.builder()
                .id("dfbgjrtg890")
                .content("Tiền thưởng cho ngày nghỉ lễ 30/4 hàng năm")
                .effectiveDate(LocalDate.now())
                .money(new BigDecimal("456"))
                .name("Thưởng 30/4")
                .build();
    }

    @Test
    @WithMockUser(username="admin",roles={"USER","ADMIN"})
    void createBenefit_validRequest_success(){
//        GIVEN
        Mockito.when(benefitRepository.save(ArgumentMatchers.any())).thenReturn(benefit);

//        WHEN
        var response = benefitService.save(benefitRequest);
//        THEN

        Assertions.assertThat(response.getId()).isEqualTo("dfbgjrtg890");

    }

    @Test
    @WithMockUser(username="admin",roles={"USER","ADMIN"})
    void updateBenefit_error_notExist(){
        when(benefitRepository.findById(anyString())).thenReturn(Optional.ofNullable(null));
        var exception = assertThrows(AppException.class, () -> benefitService.update("dfbgjrtg890",benefitRequest));
        Assertions.assertThat(exception.getErrorCode().getCode()).isEqualTo(1201);
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void updateBenefit_success(){
        when(benefitRepository.findById("dfbgjrtg890")).thenReturn(Optional.ofNullable(benefit));
        when(benefitRepository.save(benefit)).thenReturn(benefitUpdate);
        var response = benefitService.update("dfbgjrtg890",benefitUpdateRequest);

        Assertions.assertThat(response.getContent()).isEqualTo("Tiền thưởng cho ngày nghỉ lễ 30/4 hàng năm");
        Assertions.assertThat(response.getName()).isEqualTo("Thưởng 30/4");
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void findById_notFound(){
        when(benefitRepository.findById("dfbgjrtg890")).thenReturn(Optional.ofNullable(null));
        var exception = assertThrows(AppException.class, () -> benefitService.findById("dfbgjrtg890"));
        Assertions.assertThat(exception.getErrorCode().getCode()).isEqualTo(1201);
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void findById_success(){
        when(benefitRepository.findById("dfbgjrtg890")).thenReturn(Optional.ofNullable(benefit));
        var response = benefitService.findById("dfbgjrtg890");
        Assertions.assertThat(response.getId()).isEqualTo("dfbgjrtg890");
        Assertions.assertThat(response.getName()).isEqualTo("Thưởng 2/9");
    }
}
