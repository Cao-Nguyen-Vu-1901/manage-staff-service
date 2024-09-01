package com.manage_staff.mapper;


import com.manage_staff.dto.request.CertificationRequest;
import com.manage_staff.dto.request.CertificationUpdateRequest;
import com.manage_staff.dto.response.CertificationResponse;
import com.manage_staff.entity.Certification;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.lang.annotation.Target;

@Mapper(componentModel = "spring")
public interface CertificationMapper {

    @Mapping(target = "staff", ignore = true)
    Certification toCertification(CertificationRequest request);

    @Mapping(target = "staff.certifications", ignore = true)
    @Mapping(target = "staff.socialInsurance.staff", ignore = true)
    CertificationResponse toCertificationResponse( Certification certification);

    void updateCertification(@MappingTarget Certification certification, CertificationUpdateRequest request);
}
