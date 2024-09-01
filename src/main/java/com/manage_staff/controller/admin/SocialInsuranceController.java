package com.manage_staff.controller.admin;

import com.manage_staff.dto.request.SocialInsuranceRequest;
import com.manage_staff.dto.request.SocialInsuranceUpdateRequest;
import com.manage_staff.dto.response.ApiResponse;
import com.manage_staff.dto.response.CertificationResponse;
import com.manage_staff.dto.response.PagingResponse;
import com.manage_staff.dto.response.SocialInsuranceResponse;
import com.manage_staff.service.ISocialInsuranceService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/social-insurances")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class SocialInsuranceController {

    ISocialInsuranceService socialInsuranceService;

    @GetMapping
    public PagingResponse<List<SocialInsuranceResponse>> getAll(@RequestParam(defaultValue = "1") int currentPage,
                                                              @RequestParam(defaultValue = "9") int pageSize,
                                                              String type, String value, String sortBy, String orderBy){
        Page<SocialInsuranceResponse> socialInsuranceResponses =
                socialInsuranceService.paging(type,value, currentPage,pageSize,orderBy,sortBy);

        return PagingResponse.<List<SocialInsuranceResponse>>builder()
                .code(1000).currentPage(currentPage).pageSize(pageSize).sortBy(sortBy)
                .totalPage(socialInsuranceResponses.getTotalPages()).totalItem(socialInsuranceResponses.getTotalElements())
                .orderBy(orderBy)
                .type(type).value(value).result(socialInsuranceResponses.getContent())
                .build();
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
        return ApiResponse.<String>builder().result("Social insurance has been delete").build();
    }

    @PutMapping("/{id}")
    public ApiResponse<SocialInsuranceResponse> delete(@PathVariable String id,
                                                       @Valid @RequestBody SocialInsuranceUpdateRequest request){

        return ApiResponse.<SocialInsuranceResponse>builder()
                .code(1000).result(socialInsuranceService.update(id, request)).build();
    }
}
