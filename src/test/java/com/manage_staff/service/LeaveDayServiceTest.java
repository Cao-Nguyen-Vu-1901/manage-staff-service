package com.manage_staff.service;

import com.manage_staff.dto.request.LeaveDayRequest;
import com.manage_staff.dto.response.LeaveDayResponse;
import com.manage_staff.entity.LeaveDay;
import com.manage_staff.exception.AppException;
import com.manage_staff.repository.LeaveDayRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;

import java.time.LocalDate;
import java.util.Optional;

@SpringBootTest
public class LeaveDayServiceTest {

    @MockBean
    private LeaveDayRepository leaveDayRepository;

    @Autowired
    private ILeaveDayService leaveDayService;

    private LeaveDay leaveDay;
    private LeaveDayRequest request;

    @BeforeEach
    void initData(){

        leaveDay = LeaveDay.builder()
                .id("le1")
                .name("Nghỉ lễ 2/9")
                .startDate(LocalDate.of(2024, 9, 2))
                .endDate(LocalDate.of(2024, 9, 2))
                .reason("Nghỉ theo quy định nhà nước")
                .regulation("Tuân thủ nghiêm túc")
                .build();

        request = LeaveDayRequest.builder()
                .name("Nghỉ lễ 2/9")
                .startDate(LocalDate.of(2024, 9, 2))
                .endDate(LocalDate.of(2024, 9, 2))
                .reason("Nghỉ theo quy định nhà nước")
                .regulation("Tuân thủ nghiêm túc")
                .build();

    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void findById_success(){
        Mockito.when(leaveDayRepository.findById("le1")).thenReturn(Optional.of(leaveDay));
        var response = leaveDayService.findById("le1");
        Assertions.assertThat(response.getId()).isEqualTo("le1");
        Assertions.assertThat(response.getName()).isEqualTo("Nghỉ lễ 2/9");
    }

    @Test
    void saveLeaveDay(){
        leaveDay = LeaveDay.builder()
                .id("le1")
                .name("Nghỉ lễ 2/9")
                .startDate(LocalDate.of(2024, 9, 2))
                .endDate(LocalDate.of(2024, 9, 2))
                .reason("Nghỉ theo quy định nhà nước")
                .regulation("Tuân thủ nghiêm túc")
                .build();
        LeaveDay leaveDayS = LeaveDay.builder()
                .id("le1")
                .name("Nghỉ lễ 2/9")
                .startDate(LocalDate.of(2024, 9, 2))
                .endDate(LocalDate.of(2024, 9, 2))
                .reason("Nghỉ theo quy định nhà nước")
                .regulation("Tuân thủ nghiêm túc")
                .build();
        when(leaveDayRepository.save(any())).thenReturn(leaveDay);
        var response = leaveDayService.save(request);
        Assertions.assertThat(response.getId()).isEqualTo("le1");
        Assertions.assertThat(response.getName()).isEqualTo("Nghỉ lễ 2/9");

    }

    @Test
    void updateLeaveDay_error_notFound(){
        when(leaveDayRepository.findById(anyString())).thenReturn(Optional.ofNullable(null));
        var exception = assertThrows(AppException.class, ()-> leaveDayService.findById(anyString()));
        Assertions.assertThat(exception.getErrorCode().getCode()).isEqualTo(1204);
    }

}
