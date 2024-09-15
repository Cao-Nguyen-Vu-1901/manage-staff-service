package com.manage_staff.service;


import com.manage_staff.dto.request.RewardDisciplineRequest;
import com.manage_staff.entity.RewardDiscipline;
import com.manage_staff.exception.AppException;
import com.manage_staff.repository.RewardDisciplineRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.Optional;

@SpringBootTest
public class RewardDisciplineServiceTest {

    @MockBean
    private RewardDisciplineRepository rewardDisciplineRepository;

    @Autowired
    private IRewardDisciplineService rewardDisciplineService;

    private RewardDisciplineRequest request;
    private RewardDiscipline rewardDiscipline;
    private RewardDiscipline rewardDisciplineUpdate;

    @BeforeEach
    void initData(){
        request = RewardDisciplineRequest
                .builder()
                .name("Nhân viên xuất sắc nhất tháng 9")
                .content("........")
                .beginDate(LocalDate.now())
                .expiryDate(LocalDate.now())
                .creatorId("c1")
                .build();
        rewardDiscipline = RewardDiscipline
                .builder()
                .id("re1")
                .name("Nhân viên xuất sắc nhất tháng 9")
                .content("........")
                .beginDate(LocalDate.now())
                .expiryDate(LocalDate.now())
                .creatorId("c1")
                .build();
        rewardDisciplineUpdate  = RewardDiscipline
                .builder()
                .id("re1")
                .name("Nhân viên xuất sắc nhất tháng 1")
                .content(".")
                .beginDate(LocalDate.now())
                .expiryDate(LocalDate.now())
                .creatorId("c1")
                .build();
    }

    @Test
    void findById_success(){
        when(rewardDisciplineRepository.findById(anyString())).thenReturn(Optional.ofNullable(rewardDiscipline));

        var response = rewardDisciplineService.findById(anyString());

        Assertions.assertThat(response.getId()).isEqualTo("re1");
        Assertions.assertThat(response.getContent()).isEqualTo("........");
    }

    @Test
    void findById_error_notFound(){
        when(rewardDisciplineRepository.findById(anyString())).thenReturn(Optional.ofNullable(null));
        var exception = assertThrows(AppException.class, () -> rewardDisciplineService.findById(anyString()));

        Assertions.assertThat(exception.getErrorCode().getCode()).isEqualTo(1208);

    }

    @Test
    void create_success(){
        when(rewardDisciplineRepository.save(any())).thenReturn(rewardDiscipline);

        var response = rewardDisciplineService.save(request);

        Assertions.assertThat(response.getId()).isEqualTo("re1");
        Assertions.assertThat(response.getContent()).isEqualTo("........");

    }

    @Test
    void update_success(){
        when(rewardDisciplineRepository.findById(anyString())).thenReturn(Optional.ofNullable(rewardDiscipline));
        when(rewardDisciplineRepository.save(rewardDiscipline)).thenReturn(rewardDisciplineUpdate);

        var response = rewardDisciplineService.update(anyString(), request);
        Assertions.assertThat(response.getId()).isEqualTo("re1");
        Assertions.assertThat(response.getContent()).isEqualTo(".");

    }

    @Test
    void update_error_notFoundReD(){
        when(rewardDisciplineRepository.findById(anyString())).thenReturn(Optional.ofNullable(null));
        var exception = assertThrows(AppException.class, () -> rewardDisciplineService.update(anyString(),request));
        Assertions.assertThat(exception.getErrorCode().getCode()).isEqualTo(1208);
    }
}
