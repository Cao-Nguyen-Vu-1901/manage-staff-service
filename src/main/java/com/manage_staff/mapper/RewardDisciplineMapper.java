package com.manage_staff.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.manage_staff.dto.request.RewardDisciplineRequest;
import com.manage_staff.dto.response.RewardDisciplineResponse;
import com.manage_staff.entity.RewardDiscipline;

@Mapper(componentModel = "spring")
public interface RewardDisciplineMapper {
    RewardDiscipline toRewardDiscipline(RewardDisciplineRequest request);

    RewardDisciplineResponse toRewardDisciplineResponse(RewardDiscipline rewardDiscipline);

    void updateRewardDiscipline(@MappingTarget RewardDiscipline rewardDiscipline, RewardDisciplineRequest request);
}
