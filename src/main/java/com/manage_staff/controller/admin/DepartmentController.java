package com.manage_staff.controller.admin;

import com.manage_staff.dto.request.DepartmentRequest;
import com.manage_staff.dto.response.ApiResponse;
import com.manage_staff.dto.response.DepartmentResponse;
import com.manage_staff.service.IDepartmentService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/department")
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class DepartmentController {

    IDepartmentService departmentService;

    @GetMapping
    public ApiResponse<List<DepartmentResponse>> getAll(){
        return ApiResponse.<List<DepartmentResponse>>builder().
                    result(departmentService.findAll()).build();
    }

    @GetMapping("/{id}")
    public ApiResponse<DepartmentResponse> getById(@PathVariable String id){
        return ApiResponse.<DepartmentResponse>builder()
                .result(departmentService.findById(id)).build();
    }

    @PostMapping
    public ApiResponse<DepartmentResponse> save(@Valid @RequestBody DepartmentRequest request){
        return ApiResponse.<DepartmentResponse>builder()
                .result(departmentService.save(request)).build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteById(@PathVariable String id){
        departmentService.deleteById(id);
        return ApiResponse.<String>builder().result("Department has been delete").build();
    }


}
