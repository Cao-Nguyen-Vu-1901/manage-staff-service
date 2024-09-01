package com.manage_staff.controller.admin;

import com.manage_staff.dto.request.PermissionRequest;
import com.manage_staff.dto.request.PositionRequest;
import com.manage_staff.dto.request.PositionUpdateRequest;
import com.manage_staff.dto.response.ApiResponse;
import com.manage_staff.dto.response.PermissionResponse;
import com.manage_staff.dto.response.PositionResponse;
import com.manage_staff.service.IPositionService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/positions")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class PositionController {

    IPositionService positionService;

    @GetMapping
    public ApiResponse<List<PositionResponse>> getAll(){
        return ApiResponse.<List<PositionResponse>>builder().result(positionService.findAll()).build();
    }

    @GetMapping("/{id}")
    public ApiResponse<PositionResponse> getById(@PathVariable String id){
        return ApiResponse.<PositionResponse>builder().result(positionService.findById(id)).build();
    }

    @PostMapping
    public ApiResponse<PositionResponse> create(@Valid @RequestBody PositionRequest request){
        return ApiResponse.<PositionResponse>builder().result(positionService.save(request)).build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> delete( @PathVariable String id){
        positionService.deleteById(id);
        return ApiResponse.<String>builder().result("Position has been delete").build();
    }
    @PutMapping("/{id}")
    public ApiResponse<PositionResponse> update( @PathVariable String id, @RequestBody PositionUpdateRequest request){
        return ApiResponse.<PositionResponse>builder()
                .code(1000).result(positionService.update(id,request)).build();
    }
}
