package com.manage_staff.controller.admin;

import com.manage_staff.dto.request.PayrollRequest;
import com.manage_staff.dto.response.ApiResponse;
import com.manage_staff.dto.response.PayrollResponse;
import com.manage_staff.service.IPayrollService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/payroll")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PayrollController {

    IPayrollService payrollService;

    @GetMapping
    public ApiResponse<List<PayrollResponse>> getAll(){
        return ApiResponse.<List<PayrollResponse>>builder().result(payrollService.findAll()).build();
    }

    @GetMapping("/{id}")
    public ApiResponse<PayrollResponse> getById(@PathVariable String id){
        return ApiResponse.<PayrollResponse>builder().result(payrollService.findById(id)).build();
    }

    @PostMapping
    public ApiResponse<PayrollResponse> create(@Valid @RequestBody PayrollRequest request){
        return ApiResponse.<PayrollResponse>builder().result(payrollService.save(request)).build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> delete(@PathVariable String id){
        payrollService.deleteById(id);
        return ApiResponse.<String>builder().result("Payroll has been delete").build();
    }
    @PutMapping("/{id}")
    public ApiResponse<PayrollResponse> update(@PathVariable String id, @RequestBody PayrollRequest request){
        return ApiResponse.<PayrollResponse>builder()
                .code(1000).result(payrollService.update(id, request)).build();
    }
}
