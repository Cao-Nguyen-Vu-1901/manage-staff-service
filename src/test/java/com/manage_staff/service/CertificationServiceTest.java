package com.manage_staff.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.manage_staff.dao.CertificationDAO;
import com.manage_staff.dto.request.CertificationRequest;
import com.manage_staff.dto.request.CertificationUpdateRequest;
import com.manage_staff.dto.request.StaffRequest;
import com.manage_staff.dto.response.CertificationResponse;
import com.manage_staff.dto.response.StaffResponse;
import com.manage_staff.entity.Certification;
import com.manage_staff.entity.Staff;
import com.manage_staff.exception.AppException;
import com.manage_staff.repository.CertificationRepository;
import com.manage_staff.repository.StaffRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.Mockito.*;

@SpringBootTest
public class CertificationServiceTest {

    @MockBean
    CertificationRepository certificationRepository;

    @MockBean
    StaffRepository staffRepository;

    @Autowired
    ICertificationService certificationService;
    @Autowired
    IStaffService staffService;

    private Certification certification;
    private CertificationRequest certificationRequest;
    private CertificationResponse certificationResponse;
    private Staff staff;
    private StaffResponse staffResponse ;
    private StaffRequest staffRequest ;
    private LocalDate issueDate;
    private LocalDate expiryDate;
    private LocalDate dob;
    @BeforeEach
    void initData(){

        issueDate = LocalDate.of(2024, 9,2);
        expiryDate = LocalDate.of(2024, 4,2);
        dob = LocalDate.of(2003, 1,19);
        staff = Staff.builder()
                .id("9")
                .name("admin")
//                .dob(dob)
//                .gender("male")
//                .email("join@gmail.com")
//                .phoneNumber("0123456789")
//                .address("Da Nang")
//                .username("join")
//                .image("join.png")
                .build();

         staffResponse = StaffResponse.builder()
                .id("a748dd8c-9069-4b3a-ab46-d101f1345217")
                .name("admin")
//                .dob(dob)
//                .gender("male")
//                .email("join@gmail.com")
//                .phoneNumber("0123456789")
//                .address("Da Nang")
//                .username("join")
//                .image("join.png")
                .build();

         staffRequest = StaffRequest.builder()
                .name("admin")
//                .dob(dob)
//                .gender("male")
//                .email("join@gmail.com")
//                .phoneNumber("0123456789")
//                .address("Da Nang")
//                .username("join")
                .build();

        certificationRequest = CertificationRequest.builder()
                .name("abc")
                .issueDate(issueDate)
                .expiryDate(expiryDate)
                .image("abc.jpg")
                .issuingAuthority("abc")
                .staff("1")
                .build();
        certification = Certification.builder()
                .id("123a")
                .name("abc")
                .issueDate(issueDate)
                .expiryDate(expiryDate)
                .image("abc.jpg")
                .issuingAuthority("abc")
                .staff(staff).build();
        certificationResponse = CertificationResponse.builder()
                .id("123a")
                .name("abc")
                .issueDate(issueDate)
                .expiryDate(expiryDate)
                .image("abc.jpg")
                .issuingAuthority("abc")
                .staff(staffResponse).build();

    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void findById_success(){
        when(certificationRepository.findById("abcsf23")).thenReturn(Optional.ofNullable(certification));
        var response = certificationService.findById("abcsf23");
        Assertions.assertThat(response.getId()).isEqualTo("123a");
        Assertions.assertThat(response.getName()).isEqualTo("abc");
        Assertions.assertThat(response.getStaff().getId()).isEqualTo("abcsf23");
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void createCertification_success() {
        // Arrange
        Staff staff = new Staff();
        staff.setId("9");
        staff.setName("Test Staff");

        Certification certification = new Certification();
        certification.setId("123a");
        certification.setName("abc");
        certification.setStaff(staff);

        CertificationRequest certificationRequest = new CertificationRequest();
        certificationRequest.setName("abc");
        certificationRequest.setStaff("9");

        // Mocking repository calls
        when(staffRepository.findById("9")).thenReturn(Optional.of(staff));
        when(certificationRepository.save(any(Certification.class))).thenReturn(certification);


        // Act
        var response = certificationService.save(certificationRequest);

        // Assert
        Assertions.assertThat(response.getId()).isEqualTo("123a");
        Assertions.assertThat(response.getName()).isEqualTo("abc");
        Assertions.assertThat(response.getStaff().getId()).isEqualTo("9");
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void updateCertification_success() throws JsonProcessingException {
        Staff staff = new Staff();
        staff.setId("9");
        staff.setName("Test Staff");

        Certification certification = new Certification();
        certification.setId("123a");
        certification.setName("abc");
        certification.setStaff(staff);

        CertificationUpdateRequest certificationRequest = new CertificationUpdateRequest();
        certificationRequest.setName("abc");

        when(certificationRepository.findById("123a")).thenReturn(Optional.of(certification));
        when(certificationRepository.save(certification)).thenReturn(certification);

        var response = certificationService.update("123a", certificationRequest, null);

        Assertions.assertThat(response.getId()).isEqualTo("123a");
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void updateCertification_fail_id(){
        Staff staff = new Staff();
        staff.setId("9");
        staff.setName("Test Staff");

        Certification certification = new Certification();
        certification.setId("123a");
        certification.setName("abc");
        certification.setStaff(staff);

        CertificationRequest certificationRequest = new CertificationRequest();
        certificationRequest.setName("abc");
        certificationRequest.setStaff("9");

        when(certificationRepository.findById("9")).thenReturn(Optional.ofNullable(null));
        var exception = org.junit.jupiter.api.Assertions.assertThrows(AppException.class, () -> certificationService.findById("123"));

        Assertions.assertThat(exception.getErrorCode().getCode()).isEqualTo(1202);
    }
}
