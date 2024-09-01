package com.manage_staff.mapper;

import com.manage_staff.dto.request.SocialInsuranceRequest;
import com.manage_staff.dto.request.SocialInsuranceUpdateRequest;
import com.manage_staff.dto.response.SocialInsuranceResponse;
import com.manage_staff.dto.response.StaffResponse;
import com.manage_staff.entity.SocialInsurance;
import com.manage_staff.entity.Staff;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface SocialInsuranceMapper {

    @Mapping(target = "staff", ignore = true)
    SocialInsurance toSocialInsurance(SocialInsuranceRequest request);

    @Mapping(target = "staff.socialInsurance", ignore = true)
    @Mapping(target = "staff.certifications", ignore = true)
    SocialInsuranceResponse toSocialInsuranceResponse(SocialInsurance socialInsurance);

    @Mapping(target = "staff", ignore = true)
    void updateSocialInsurance(@MappingTarget SocialInsurance socialInsurance, SocialInsuranceUpdateRequest request);
}
