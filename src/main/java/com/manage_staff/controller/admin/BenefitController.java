package com.manage_staff.controller.admin;

import com.manage_staff.dto.request.BenefitRequest;
import com.manage_staff.dto.response.ApiResponse;
import com.manage_staff.dto.response.BenefitResponse;
import com.manage_staff.dto.response.PagingResponse;
import com.manage_staff.dto.response.StaffResponse;
import com.manage_staff.service.IBenefitService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/benefits")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class BenefitController {

    IBenefitService benefitService;

    @GetMapping
    public PagingResponse<List<BenefitResponse>> getAllBenefits(@RequestParam(defaultValue = "1") int currentPage,
                                                                @RequestParam(defaultValue = "9") int pageSize,
                                                                String type, String value, String sortBy, String orderBy){
        Page<BenefitResponse> benefitResponsePage = benefitService.paging(type,value, currentPage,pageSize,orderBy,sortBy);

        return PagingResponse.<List<BenefitResponse>>builder()
                .code(1000).currentPage(currentPage ).pageSize(pageSize).sortBy(sortBy)
                .totalPage(benefitResponsePage.getTotalPages()).totalItem(benefitResponsePage.getTotalElements())
                .orderBy(orderBy)
                .type(type).value(value).result(benefitResponsePage.getContent())
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<BenefitResponse> getById(@PathVariable String id){
        return ApiResponse.<BenefitResponse>builder().result(benefitService.findById(id)).build();
    }

    @PostMapping
    public ApiResponse<BenefitResponse> create(@Valid @RequestBody BenefitRequest request){
        return ApiResponse.<BenefitResponse>builder()
                .result(benefitService.save(request)).build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> delete(@PathVariable String id){
        benefitService.deleteById(id);
        return ApiResponse.<String>builder().result("Benefit has been delete").build();
    }

    @PutMapping("/{id}")
    public ApiResponse<BenefitResponse> update(@PathVariable String id, @Valid @RequestBody BenefitRequest request){
        return ApiResponse.<BenefitResponse>builder()
                .code(1000).result(benefitService.update(id, request)).build();
    }
}
