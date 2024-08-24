package com.manage_staff.controller.admin;

import com.manage_staff.dto.request.PositionRequest;
import com.manage_staff.dto.request.RewardDisciplineRequest;
import com.manage_staff.dto.response.ApiResponse;
import com.manage_staff.dto.response.PositionResponse;
import com.manage_staff.dto.response.RewardDisciplineResponse;
import com.manage_staff.service.IRewardDisciplineService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/reward-discipline")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class RewardDisciplineController {

    IRewardDisciplineService rewardDisciplineService;

    @GetMapping
    public ApiResponse<List<RewardDisciplineResponse>> getAll(){
        return ApiResponse.<List<RewardDisciplineResponse>>builder().result(rewardDisciplineService.findAll()).build();
    }

    @GetMapping("/{id}")
    public ApiResponse<RewardDisciplineResponse> getById(@PathVariable String id){
        return ApiResponse.<RewardDisciplineResponse>builder().result(rewardDisciplineService.findById(id)).build();
    }

    @PostMapping
    public ApiResponse<RewardDisciplineResponse> save(@Valid @RequestBody RewardDisciplineRequest request){
        return ApiResponse.<RewardDisciplineResponse>builder().result(rewardDisciplineService.save(request)).build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> delete( @PathVariable String id){
        rewardDisciplineService.deleteById(id);
        return ApiResponse.<String>builder().result("Reward discipline has been delete").build();
    }

    @PutMapping("/{id}")
    public ApiResponse<RewardDisciplineResponse> update( @PathVariable String id,
                                                        @Valid @RequestBody RewardDisciplineRequest request){
        return ApiResponse.<RewardDisciplineResponse>builder()
                .code(1000).result(rewardDisciplineService.update(id, request)).build();
    }
}
