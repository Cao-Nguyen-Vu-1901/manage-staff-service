package com.manage_staff.controller.admin;

import com.manage_staff.dto.request.SocialInsuranceRequest;
import com.manage_staff.dto.response.ApiResponse;
import com.manage_staff.dto.response.SocialInsuranceResponse;
import com.manage_staff.service.ISocialInsuranceService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/social-insurance")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class SocialInsuranceController {

    ISocialInsuranceService socialInsuranceService;

    @GetMapping
    public ApiResponse<List<SocialInsuranceResponse>> getAll(){
        return ApiResponse.<List<SocialInsuranceResponse>>builder().result(socialInsuranceService.findAll()).build();
    }

    @GetMapping("/{id}")
    public ApiResponse<SocialInsuranceResponse> getById(@PathVariable String id){
        return ApiResponse.<SocialInsuranceResponse>builder().result(socialInsuranceService.findById(id)).build();
    }

    @PostMapping
    public ApiResponse<SocialInsuranceResponse> save(@Valid @RequestBody SocialInsuranceRequest request){
        return ApiResponse.<SocialInsuranceResponse>builder().result(socialInsuranceService.save(request)).build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> delete(@PathVariable String id){
        socialInsuranceService.deleteById(id);
        return ApiResponse.<String>builder().result("Permission has been delete").build();
    }
}
