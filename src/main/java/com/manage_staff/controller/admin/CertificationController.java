package com.manage_staff.controller.admin;

import com.manage_staff.dto.request.CertificationRequest;
import com.manage_staff.dto.request.CertificationUpdateRequest;
import com.manage_staff.dto.request.SocialInsuranceRequest;
import com.manage_staff.dto.response.*;
import com.manage_staff.service.ICertificationService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/certification")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class CertificationController {

    ICertificationService certificationService;

    @GetMapping
    public PagingResponse<List<CertificationResponse>> getAll( @RequestParam(defaultValue = "1") int currentPage,
                                                                @RequestParam(defaultValue = "9") int pageSize,
                                                                String type, String value, String sortBy, String orderBy){
        Page<CertificationResponse> certificationResponses =
                certificationService.paging(type,value, currentPage,pageSize,orderBy,sortBy);

        return PagingResponse.<List<CertificationResponse>>builder()
                .code(1000).currentPage(currentPage).pageSize(pageSize).sortBy(sortBy)
                .totalPage(certificationResponses.getTotalPages()).totalItem(certificationResponses.getTotalElements())
                .orderBy(orderBy)
                .type(type).value(value).result(certificationResponses.getContent())
                .build();
    }
    @GetMapping("/{id}")
    public ApiResponse<CertificationResponse> getById(@PathVariable String id){
        return ApiResponse.<CertificationResponse>builder().result(certificationService.findById(id)).build();
    }

    @PostMapping
    public ApiResponse<CertificationResponse> create(@Valid @RequestBody CertificationRequest request){
        return ApiResponse.<CertificationResponse>builder().result(certificationService.save(request)).build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> delete(@PathVariable String id){
        certificationService.deleteById(id);
        return ApiResponse.<String>builder().result("Permission has been delete").build();
    }
    @PutMapping("/{id}")
    public ApiResponse<CertificationResponse> update(@PathVariable String id, @RequestBody CertificationUpdateRequest request){
        certificationService.update(id, request);
        return ApiResponse.<CertificationResponse>builder()
                .result(certificationService.update(id, request)).build();
    }
}
