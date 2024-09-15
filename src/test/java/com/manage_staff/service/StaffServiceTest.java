package com.manage_staff.service;

import com.manage_staff.dto.request.StaffRequest;
import com.manage_staff.dto.request.StaffUpdateRequest;
import com.manage_staff.dto.response.StaffResponse;
import com.manage_staff.entity.Role;
import com.manage_staff.entity.Staff;
import com.manage_staff.exception.AppException;
import com.manage_staff.repository.RoleRepository;
import com.manage_staff.repository.StaffRepository;

import com.manage_staff.util.ProcessImage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import org.assertj.core.api.Assertions;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class StaffServiceTest {

    @MockBean
    private StaffRepository staffRepository;

    @MockBean
    private RoleRepository roleRepository;

    @Autowired
    private IStaffService staffService;

    private Staff staff;
    private Staff staffUpdate;

    private StaffRequest staffRequest;
    private StaffUpdateRequest staffUpdateRequest;

    @BeforeEach
    void initData(){
        LocalDate issueDate = LocalDate.of(2024, 9,2);
        LocalDate expiryDate = LocalDate.of(2024, 4,2);
        LocalDate dob = LocalDate.of(2003, 1,19);
        staff = Staff.builder()
                .id("9")
                .name("admin 1")
                .dob(dob)
                .gender("male")
                .email("join@gmail.com")
                .phoneNumber("0123456789")
                .address("Da Nang")
                .username("join")
                .image("join.png")
                .roles(new HashSet<>())
                .build();

        staffUpdate = Staff.builder()
                .id("9")
                .name("admin 2")
                .dob(dob)
                .gender("male")
                .email("join@gmail.com")
                .phoneNumber("0123456789")
                .address("Da Nang")
                .username("join")
                .roles(new HashSet<>())
                .image("join.png")
                .build();

        staffRequest = StaffRequest.builder()
                .name("admin 1")
                .dob(dob)
                .gender("male")
                .email("join@gmail.com")
                .phoneNumber("0123456789")
                .address("Da Nang")
                .roles(new HashSet<>(List.of("ADMIN")))
                .password("123456")
                .username("join")
                .build();
        staffUpdateRequest = StaffUpdateRequest.builder()
                .name("admin 1")
                .dob(dob)
                .gender("male")
                .email("join@gmail.com")
                .phoneNumber("0123456789")
                .roles(new HashSet<>(List.of("ADMIN")))
                .address("Da Nang")
                .build();

    }

    @Test
//    @WithMockUser(username = "join")
    void findById_success(){
        when(staffRepository.findById(anyString())).thenReturn(Optional.ofNullable(staff));
        var response = staffService.findById(anyString());
        Assertions.assertThat(response.getId()).isEqualTo("9");
    }

    @Test
    void findById_error(){
        when(staffRepository.findById(anyString())).thenReturn(Optional.ofNullable(null));
        var exception = assertThrows(AppException.class, () -> staffService.findById(anyString()));

        Assertions.assertThat(exception.getErrorCode().getCode()).isEqualTo(1211);
    }

    @Test
    void create_error_usernameExisted(){
        when(staffRepository.existsByUsername(anyString())).thenReturn(true);
        var exception = assertThrows(AppException.class, () -> staffService.save(staffRequest, any()));

        Assertions.assertThat(exception.getErrorCode().getCode()).isEqualTo(1110);
    }
    @Test
    void create_error_file() throws IOException {
        when(staffRepository.existsByUsername(anyString())).thenReturn(false);
        mockStatic(ProcessImage.class);
        when(ProcessImage.upload(any(MultipartFile.class), anyString())).thenReturn(null );
        var exception = assertThrows(AppException.class, () -> staffService.save(staffRequest, any()));

        Assertions.assertThat(exception.getErrorCode().getCode()).isEqualTo(1404);
    }
    @Test
    void create_error_role()  {
        staffRequest.setRoles(null);
        when(staffRepository.existsByUsername(anyString())).thenReturn(false);
        when(roleRepository.findAllById(any())).thenReturn(List.of());
        var exception = assertThrows(AppException.class, () -> staffService.save(staffRequest, any()));
        Assertions.assertThat(exception.getErrorCode().getCode()).isEqualTo(1209);
    }

    @Test
    void create_success() throws IOException {
        // Arrange
        MultipartFile file = mock(MultipartFile.class); // Mock the file
        when(file.getOriginalFilename()).thenReturn("image.png");
//        when(file.isEmpty()).thenReturn(false); // Ensure the file is not empty

        // Mock repository behavior to ensure staff with the same username doesn't exist
        when(staffRepository.existsByUsername(anyString())).thenReturn(false);

        // Mock the ProcessImage.upload() method
        mockStatic(ProcessImage.class); // Mock static method
        when(ProcessImage.upload(any(MultipartFile.class), anyString())).thenReturn("staf");

        // Mock saving the staff to the repository

        when(staffRepository.save(any())).thenReturn(staff);

        // Act
        var response = staffService.save(staffRequest, file);  // Pass the mocked file

        // Assert
        Assertions.assertThat(response.getId()).isEqualTo("9");  // Check that the ID is correct
    }

    @Test
    void update_success() throws IOException {
        // Mock the file
        staffUpdateRequest.setRoles((new HashSet<>(List.of("ADMIN"))));
        MultipartFile file = mock(MultipartFile.class); // Mock the file
        when(file.getOriginalFilename()).thenReturn("image.png");
//        when(file.isEmpty()).thenReturn(false); // Ensure the file is not empty

        // Mock repository behavior to ensure staff with the same username doesn't exist
        when(staffRepository.findById(anyString())).thenReturn(Optional.ofNullable(staff));
        when(roleRepository.findAllById(any())).thenReturn(List.of());

        // Mock the ProcessImage.upload() method
        mockStatic(ProcessImage.class); // Mock static method
        when(ProcessImage.upload(any(MultipartFile.class), anyString())).thenReturn("staf");


        when(staffRepository.save(any())).thenReturn(staffUpdate);
        var response = staffService.update("9",staffUpdateRequest , file);
        Assertions.assertThat(response.getId()).isEqualTo("9");
    }

    @Test
    void update_error_role() throws IOException {
        // Mock the file
        staffUpdateRequest.setRoles(null);
        MultipartFile file = mock(MultipartFile.class); // Mock the file
        when(file.getOriginalFilename()).thenReturn("image.png");
//        when(file.isEmpty()).thenReturn(false); // Ensure the file is not empty

        // Mock repository behavior to ensure staff with the same username doesn't exist
        when(staffRepository.findById(anyString())).thenReturn(Optional.ofNullable(staff));
        when(roleRepository.findAllById(any())).thenReturn(List.of());

        // Mock the ProcessImage.upload() method
        mockStatic(ProcessImage.class);
        when(ProcessImage.upload(any(MultipartFile.class), anyString())).thenReturn(null );



        var exception = assertThrows(AppException.class, () -> staffService.update("9",staffUpdateRequest , file));
        Assertions.assertThat(exception.getErrorCode().getCode()).isEqualTo(1209);
    }

}
