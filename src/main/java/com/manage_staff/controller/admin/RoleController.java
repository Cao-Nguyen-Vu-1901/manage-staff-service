package com.manage_staff.controller.admin;

import com.manage_staff.dto.request.RoleRequest;
import com.manage_staff.dto.response.ApiResponse;
import com.manage_staff.dto.response.RoleResponse;
import com.manage_staff.service.IRoleService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/role")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class RoleController {

    IRoleService roleService;

    @GetMapping
    public ApiResponse<List<RoleResponse>> getAll(){
        return ApiResponse.<List<RoleResponse>>builder().result(roleService.findAll()).build();
    }

    @GetMapping("/{id}")
    public ApiResponse<RoleResponse> getById(@PathVariable String id){
        return ApiResponse.<RoleResponse>builder().result(roleService.findById(id)).build();
    }

    @PostMapping
    public ApiResponse<RoleResponse> save(@Valid @RequestBody RoleRequest request){
        return ApiResponse.<RoleResponse>builder().result(roleService.save(request)).build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> delete( @PathVariable String id){
        roleService.deleteById(id);
        return ApiResponse.<String>builder().result("Role has been delete").build();
    }
}
