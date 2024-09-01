package com.manage_staff.controller.admin;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.manage_staff.dto.request.CertificationRequest;
import com.manage_staff.dto.request.CertificationUpdateRequest;
import com.manage_staff.dto.request.SocialInsuranceRequest;
import com.manage_staff.dto.response.ApiResponse;
import com.manage_staff.dto.response.CertificationResponse;
import com.manage_staff.dto.response.SocialInsuranceResponse;
import com.manage_staff.service.ICertificationService;
import com.manage_staff.util.ProcessImage;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/admin/certification")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class CertificationController {

    ICertificationService certificationService;
    ObjectMapper objectMapper;

    @GetMapping
    public ApiResponse<List<CertificationResponse>> getAll(){
        return ApiResponse.<List<CertificationResponse>>builder().result(certificationService.findAll()).build();
    }

    @GetMapping("/{id}")
    public ApiResponse<CertificationResponse> getById(@PathVariable String id){
        return ApiResponse.<CertificationResponse>builder().result(certificationService.findById(id)).build();
    }

    @PostMapping
    public ApiResponse<CertificationResponse> create(@RequestParam("file") MultipartFile file, String request) throws IOException {
        var certification = objectMapper.readValue( request , CertificationRequest.class);
        String filePath = ProcessImage.upload(file,"certifications/");
        certification.setImage(filePath);
        var response = certificationService.save(certification);

        return ApiResponse.<CertificationResponse>builder().result(response).build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> delete(@PathVariable String id){
        certificationService.deleteById(id);
        return ApiResponse.<String>builder().result("Permission has been delete").build();
    }
    @PutMapping
    public ApiResponse<CertificationResponse> update(@RequestParam String id, @RequestParam String request,
                                                     @RequestParam(value = "file",required = false) MultipartFile file) throws JsonProcessingException {

        return ApiResponse.<CertificationResponse>builder()
                .result(certificationService.update(id, request, file)).build();
    }
}
