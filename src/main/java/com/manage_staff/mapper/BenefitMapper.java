package com.manage_staff.mapper;

import com.manage_staff.dto.request.BenefitRequest;
import com.manage_staff.dto.response.BenefitResponse;
import com.manage_staff.entity.Benefit;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BenefitMapper {
    Benefit toBenefit(BenefitRequest request);

    BenefitResponse toBenefitResponse(Benefit benefit);
}
