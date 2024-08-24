package com.manage_staff.controller.admin;

import com.manage_staff.dto.request.StaffRequest;
import com.manage_staff.dto.request.StaffUpdateRequest;
import com.manage_staff.dto.response.ApiResponse;
import com.manage_staff.dto.response.PagingResponse;
import com.manage_staff.dto.response.StaffResponse;
import com.manage_staff.entity.Staff;
import com.manage_staff.mapper.PositionMapper;
import com.manage_staff.service.IStaffService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin/staff")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class StaffController {

    IStaffService staffService;
    private final PositionMapper positionMapper;


    @GetMapping
    public ApiResponse<List<StaffResponse>> findAll(){
        return ApiResponse.<List<StaffResponse>>builder()
                .code(1000).result(
                        staffService.findAll()
                ).build();
    }
    @GetMapping("/{id}")
    public ApiResponse<StaffResponse> findById(@PathVariable String id){
        return ApiResponse.<StaffResponse>builder()
                .code(1000).result(
                        staffService.findById(id)
                ).build();
    }


    @PostMapping
    public ApiResponse<StaffResponse> save(@Valid @RequestBody StaffRequest request){
        return ApiResponse.<StaffResponse>builder()
                .code(1000).result(staffService.save(request))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> delete (@PathVariable String id){
        staffService.deleteById(id);
        return ApiResponse.<String>builder()
                .message("Staff have been delete")
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<StaffResponse> delete (@PathVariable String id,
                                              @Valid @RequestBody StaffUpdateRequest request){

        return ApiResponse.<StaffResponse>builder()
                .code(1000).result(staffService.update(id, request))
                .build();
    }
}
