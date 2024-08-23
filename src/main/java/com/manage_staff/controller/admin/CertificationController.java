package com.manage_staff.controller.admin;

import com.manage_staff.dto.request.CertificationRequest;
import com.manage_staff.dto.request.SocialInsuranceRequest;
import com.manage_staff.dto.response.ApiResponse;
import com.manage_staff.dto.response.CertificationResponse;
import com.manage_staff.dto.response.SocialInsuranceResponse;
import com.manage_staff.service.ICertificationService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/certification")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class CertificationController {

    ICertificationService certificationService;

    @GetMapping
    public ApiResponse<List<CertificationResponse>> getAll(){
        return ApiResponse.<List<CertificationResponse>>builder().result(certificationService.findAll()).build();
    }

    @GetMapping("/{id}")
    public ApiResponse<CertificationResponse> getById(@PathVariable String id){
        return ApiResponse.<CertificationResponse>builder().result(certificationService.findById(id)).build();
    }

    @PostMapping
    public ApiResponse<CertificationResponse> save(@Valid @RequestBody CertificationRequest request){
        return ApiResponse.<CertificationResponse>builder().result(certificationService.save(request)).build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> delete(@PathVariable String id){
        certificationService.deleteById(id);
        return ApiResponse.<String>builder().result("Permission has been delete").build();
    }
}
