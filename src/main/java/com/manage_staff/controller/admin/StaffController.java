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
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin/staff")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class StaffController {

    IStaffService staffService;


    @GetMapping("/{id}")
    public ApiResponse<StaffResponse> findById(@PathVariable String id){
        return ApiResponse.<StaffResponse>builder()
                .code(1000).result(
                        staffService.findById(id)
                ).build();
    }

<<<<<<< HEAD
=======

>>>>>>> 4fb93866b5e43bde319c76341ed10b1a80862474
    @GetMapping
    public PagingResponse<List<StaffResponse>> paging(@RequestParam(defaultValue = "1") int currentPage,
                                                      @RequestParam(defaultValue = "9") int pageSize,
                                                      String type, String value, String sortBy, String orderBy){
        Page<StaffResponse> staff = staffService
                    .pagingStaff(currentPage, pageSize, type, value, sortBy, orderBy) ;

        return PagingResponse.<List<StaffResponse>>builder()
                .code(1000).currentPage(currentPage).pageSize(pageSize).sortBy(sortBy)
                .totalPage(staff.getTotalPages()).totalItem(staff.getTotalElements())
                .orderBy(orderBy)
                .type(type).value(value).result(staff.getContent())
                .build();
    }

    @PostMapping
    public ApiResponse<StaffResponse> save(@Valid StaffRequest request, @RequestParam("file") MultipartFile file) throws IOException {
        return ApiResponse.<StaffResponse>builder()
                .code(1000).result(staffService.save(request, file))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> delete (@PathVariable String id){
        staffService.deleteById(id);
        return ApiResponse.<String>builder()
                .message("Staff have been delete")
                .build();
    }

    @PutMapping
    public ApiResponse<StaffResponse> update (String id,
                                              @Valid StaffUpdateRequest request,
                                              @RequestParam(value = "file", required = false) MultipartFile file){

        return ApiResponse.<StaffResponse>builder()
                .code(1000).result(staffService.update(id, request, file))
                .build();
    }

}
