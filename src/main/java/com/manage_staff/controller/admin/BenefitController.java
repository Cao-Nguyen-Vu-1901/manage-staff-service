package com.manage_staff.controller.admin;

import com.manage_staff.dto.request.BenefitRequest;
import com.manage_staff.dto.response.ApiResponse;
import com.manage_staff.dto.response.BenefitResponse;
import com.manage_staff.service.IBenefitService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/benefit")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class BenefitController {

    IBenefitService benefitService;

    @GetMapping
    public ApiResponse<List<BenefitResponse>> getAllBenefits(){
        return ApiResponse.<List<BenefitResponse>>builder()
                .result(benefitService.findAll()).build();
    }

    @GetMapping("/{id}")
    public ApiResponse<BenefitResponse> getById(@PathVariable String id){
        return ApiResponse.<BenefitResponse>builder().result(benefitService.findById(id)).build();
    }

    @PostMapping
    public ApiResponse<BenefitResponse> save(@Valid @RequestBody BenefitRequest request){
        return ApiResponse.<BenefitResponse>builder()
                .result(benefitService.save(request)).build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> delete(@PathVariable String id){
        benefitService.deleteById(id);
        return ApiResponse.<String>builder().result("Benefit has been delete").build();
    }
}
