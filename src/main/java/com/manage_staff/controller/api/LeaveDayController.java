package com.manage_staff.controller.api;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import com.manage_staff.dto.request.LeaveDayRequest;
import com.manage_staff.dto.response.ApiResponse;
import com.manage_staff.dto.response.LeaveDayResponse;
import com.manage_staff.dto.response.PagingResponse;
import com.manage_staff.service.ILeaveDayService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RestController
@RequestMapping("/api/leave-days")
public class LeaveDayController {

    ILeaveDayService leaveDayService;

    @GetMapping
    public PagingResponse<List<LeaveDayResponse>> getAll(
            @RequestParam(defaultValue = "1") int currentPage,
            @RequestParam(defaultValue = "9") int pageSize,
            String type,
            String value,
            String sortBy,
            String orderBy) {
        Page<LeaveDayResponse> leaveDayResponses =
                leaveDayService.paging(type, value, currentPage, pageSize, orderBy, sortBy);

        return PagingResponse.<List<LeaveDayResponse>>builder()
                .code(1000)
                .currentPage(currentPage)
                .pageSize(pageSize)
                .sortBy(sortBy)
                .totalPage(leaveDayResponses.getTotalPages())
                .totalItem(leaveDayResponses.getTotalElements())
                .orderBy(orderBy)
                .type(type)
                .value(value)
                .result(leaveDayResponses.getContent())
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<LeaveDayResponse> getById(@PathVariable String id) {
        return ApiResponse.<LeaveDayResponse>builder()
                .result(leaveDayService.findById(id))
                .build();
    }

    @PostMapping
    public ApiResponse<LeaveDayResponse> create(@Valid @RequestBody LeaveDayRequest leaveDayRequest) {
        return ApiResponse.<LeaveDayResponse>builder()
                .result(leaveDayService.save(leaveDayRequest))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> delete(@PathVariable String id) {
        leaveDayService.deleteById(id);
        return ApiResponse.<String>builder().result("Leave day has been delete").build();
    }

    @PutMapping("/{id}")
    public ApiResponse<LeaveDayResponse> update(@PathVariable String id, @RequestBody LeaveDayRequest request) {
        return ApiResponse.<LeaveDayResponse>builder()
                .code(1000)
                .result(leaveDayService.update(id, request))
                .build();
    }
}
