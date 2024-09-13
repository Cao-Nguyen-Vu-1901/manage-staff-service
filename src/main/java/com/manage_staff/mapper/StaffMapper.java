package com.manage_staff.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import com.manage_staff.dto.request.StaffRequest;
import com.manage_staff.dto.request.StaffUpdateRequest;
import com.manage_staff.dto.response.CertificationResponse;
import com.manage_staff.dto.response.StaffResponse;
import com.manage_staff.entity.Certification;
import com.manage_staff.entity.Staff;

@Mapper(componentModel = "spring")
public interface StaffMapper {

    @Mapping(target = "certifications", ignore = true)
    @Mapping(target = "socialInsurance", ignore = true)
    @Mapping(target = "rewardDisciplines", ignore = true)
    @Mapping(target = "leaves", ignore = true)
    @Mapping(target = "benefits", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "image", ignore = true)
    Staff toStaff(StaffRequest request);

    @Mapping(target = "certifications", source = "certifications", qualifiedByName = "mapCertifications")
    @Mapping(target = "socialInsurance.staff", ignore = true)
    StaffResponse toStaffResponse(Staff staff);

    @Named("mapCertifications")
    default List<CertificationResponse> certificationListToCertificationResponseList(
            List<Certification> certifications) {
        if (certifications == null) {
            return new ArrayList<>();
        }
        return certifications.stream()
                .map(this::certificationToCertificationResponse)
                .collect(Collectors.toList());
    }

    @Mapping(target = "staff", ignore = true)
    CertificationResponse certificationToCertificationResponse(Certification certification);

    @Mapping(target = "certifications", ignore = true)
    @Mapping(target = "socialInsurance", ignore = true)
    @Mapping(target = "rewardDisciplines", ignore = true)
    @Mapping(target = "leaves", ignore = true)
    @Mapping(target = "benefits", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "image", ignore = true)
    void updateStaff(@MappingTarget Staff staff, StaffUpdateRequest request);
}
