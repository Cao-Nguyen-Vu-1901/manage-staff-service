package com.manage_staff.mapper;


import com.manage_staff.dto.request.CertificationRequest;
import com.manage_staff.dto.response.CertificationResponse;
import com.manage_staff.entity.Certification;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CertificationMapper {
    Certification toCertification(CertificationRequest request);

    CertificationResponse toCertificationResponse( Certification certification);
}
