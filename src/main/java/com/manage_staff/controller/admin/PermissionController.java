package com.manage_staff.controller.admin;

import com.manage_staff.dto.request.PermissionRequest;
import com.manage_staff.dto.response.ApiResponse;
import com.manage_staff.dto.response.PermissionResponse;
import com.manage_staff.service.IPermissionService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/permissions")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class PermissionController {

    IPermissionService permissionService;

    @GetMapping
    public ApiResponse<List<PermissionResponse>> getAll(){
        return ApiResponse.<List<PermissionResponse>>builder().result(permissionService.findAll()).build();
    }

    @GetMapping("/{id}")
    public ApiResponse<PermissionResponse> getById(@PathVariable String id){
        return ApiResponse.<PermissionResponse>builder().result(permissionService.findById(id)).build();
    }

    @PostMapping
    public ApiResponse<PermissionResponse> create(@Valid @RequestBody PermissionRequest request){
        return ApiResponse.<PermissionResponse>builder().result(permissionService.save(request)).build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> delete( @PathVariable String id){
        permissionService.deleteById(id);
        return ApiResponse.<String>builder().result("Permission has been delete").build();
    }

}
