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

    @GetMapping
    public PagingResponse<List<StaffResponse>> paging(@RequestParam(defaultValue = "1") int currentPage,
                                                      @RequestParam(defaultValue = "9") int pageSize,
                                                      String type, String value, String sortBy, String orderBy){
        Page<StaffResponse> staff = staffService
                    .findAllByDobPaging(currentPage, pageSize, type, value, sortBy, orderBy) ;

        return PagingResponse.<List<StaffResponse>>builder()
                .code(1000).currentPage(currentPage + 1).pageSize(pageSize).sortBy(sortBy)
                .totalPage(staff.getTotalPages()).totalItem(staff.getTotalElements())
                .orderBy(orderBy)
                .type(type).value(value).result(staff.getContent())
                .build();
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
