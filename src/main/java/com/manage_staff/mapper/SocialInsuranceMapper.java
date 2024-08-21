package com.manage_staff.mapper;

import com.manage_staff.dto.request.SocialInsuranceRequest;
import com.manage_staff.dto.response.SocialInsuranceResponse;
import com.manage_staff.entity.SocialInsurance;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SocialInsuranceMapper {

    SocialInsurance toSocialInsurance(SocialInsuranceRequest request);
    SocialInsuranceResponse toSocialInsuranceResponse(SocialInsurance socialInsurance);
}
