package com.manage_staff.service;


import com.manage_staff.dto.request.CertificationRequest;
import com.manage_staff.dto.response.CertificationResponse;
import com.manage_staff.entity.Certification;

import java.util.List;

public interface ICertificationService {

    List<CertificationResponse> findAll();
    List<CertificationResponse> findAllById( List<String> ids);
    CertificationResponse findById( String id);

    CertificationResponse save(CertificationRequest request);

    void deleteById(String id);
    void deleteAllById(List<String> ids);
    void deleteAll();
}
