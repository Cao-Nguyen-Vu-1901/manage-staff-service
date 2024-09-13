package com.manage_staff.controller.api;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import com.manage_staff.dto.request.DepartmentRequest;
import com.manage_staff.dto.response.ApiResponse;
import com.manage_staff.dto.response.DepartmentResponse;
import com.manage_staff.dto.response.PagingResponse;
import com.manage_staff.service.IDepartmentService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/api/department")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class DepartmentController {

    IDepartmentService departmentService;

    @GetMapping
    public PagingResponse<List<DepartmentResponse>> getAll(
            @RequestParam(defaultValue = "1") int currentPage,
            @RequestParam(defaultValue = "9") int pageSize,
            String type,
            String value,
            String sortBy,
            String orderBy) {
        Page<DepartmentResponse> departmentResponses =
                departmentService.paging(type, value, currentPage, pageSize, orderBy, sortBy);

        return PagingResponse.<List<DepartmentResponse>>builder()
                .code(1000)
                .currentPage(currentPage)
                .pageSize(pageSize)
                .sortBy(sortBy)
                .totalPage(departmentResponses.getTotalPages())
                .totalItem(departmentResponses.getTotalElements())
                .orderBy(orderBy)
                .type(type)
                .value(value)
                .result(departmentResponses.getContent())
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<DepartmentResponse> getById(@PathVariable String id) {
        return ApiResponse.<DepartmentResponse>builder()
                .result(departmentService.findById(id))
                .build();
    }

    @PostMapping
    public ApiResponse<DepartmentResponse> create(@Valid @RequestBody DepartmentRequest request) {
        return ApiResponse.<DepartmentResponse>builder()
                .result(departmentService.save(request))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteById(@PathVariable String id) {
        departmentService.deleteById(id);
        return ApiResponse.<String>builder()
                .result("Department has been delete")
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<DepartmentResponse> update(@PathVariable String id, @RequestBody DepartmentRequest request) {
        return ApiResponse.<DepartmentResponse>builder()
                .code(1000)
                .result(departmentService.update(id, request))
                .build();
    }
}
