package com.manage_staff.mapper;

import com.manage_staff.dto.request.SocialInsuranceRequest;
import com.manage_staff.dto.response.SocialInsuranceResponse;
import com.manage_staff.dto.response.StaffResponse;
import com.manage_staff.entity.SocialInsurance;
import com.manage_staff.entity.Staff;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface SocialInsuranceMapper {

    @Mapping(target = "staff", ignore = true)
    SocialInsurance toSocialInsurance(SocialInsuranceRequest request);

//    @Mapping(target = "staff", source = "staff", qualifiedByName = "mapStaff")
    @Mapping(target = "staff.socialInsurance", ignore = true)
    @Mapping(target = "staff.certifications", ignore = true)
    SocialInsuranceResponse toSocialInsuranceResponse(SocialInsurance socialInsurance);

//    @Named("mapStaff")
//    default List<StaffResponse> staffListToStaffResponseList (List<Staff> staff){
//        if(staff == null){
//            return new ArrayList<>();
//        }
//        return staff.stream().map(this :: toStaffResponse).collect(Collectors.toList());
//    }
//
//    @Mapping(target = "certifications" , ignore = true)
//    @Mapping(target = "socialInsurance" , ignore = true)
//    StaffResponse toStaffResponse (Staff staff);
    }
