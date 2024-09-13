package com.manage_staff.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.manage_staff.dto.request.BenefitRequest;
import com.manage_staff.dto.response.BenefitResponse;
import com.manage_staff.entity.Benefit;

@Mapper(componentModel = "spring")
public interface BenefitMapper {
    Benefit toBenefit(BenefitRequest request);

    BenefitResponse toBenefitResponse(Benefit benefit);

    void updateBenefit(@MappingTarget Benefit benefit, BenefitRequest request);
}
