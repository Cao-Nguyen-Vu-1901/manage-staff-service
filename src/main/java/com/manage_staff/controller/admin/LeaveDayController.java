package com.manage_staff.controller.admin;

import com.manage_staff.dto.request.LeaveDayRequest;
import com.manage_staff.dto.response.ApiResponse;
import com.manage_staff.dto.response.LeaveDayResponse;
import com.manage_staff.service.ILeaveDayService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RestController
@RequestMapping("/admin/leave-day")
public class LeaveDayController {

    ILeaveDayService leaveDayService;

    @GetMapping
    public ApiResponse<List<LeaveDayResponse>> getAll(){
        return ApiResponse.<List<LeaveDayResponse>>builder()
                .result(leaveDayService.findAll()).build();
    }

    @GetMapping("/{id}")
    public ApiResponse<LeaveDayResponse> getById(@PathVariable String id){
        return ApiResponse.<LeaveDayResponse>builder().result(leaveDayService.findById(id)).build();
    }

    @PostMapping
    public ApiResponse<LeaveDayResponse> save(@Valid @RequestBody LeaveDayRequest leaveDayRequest){
        return ApiResponse.<LeaveDayResponse>builder().result(leaveDayService.save(leaveDayRequest)).build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> delete(@PathVariable String id){
        leaveDayService.deleteById(id);
        return ApiResponse.<String>builder().result("Leave day has been delete").build();
    }
}
