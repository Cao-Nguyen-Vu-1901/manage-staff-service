package com.manage_staff.mapper;

import com.manage_staff.dto.request.RewardDisciplineRequest;
import com.manage_staff.dto.response.RewardDisciplineResponse;
import com.manage_staff.entity.RewardDiscipline;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RewardDisciplineMapper {
    RewardDiscipline toRewardDiscipline (RewardDisciplineRequest request);

    RewardDisciplineResponse toRewardDisciplineResponse (RewardDiscipline rewardDiscipline);
}
