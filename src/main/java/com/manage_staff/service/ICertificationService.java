package com.manage_staff.service;


import com.manage_staff.dto.request.CertificationRequest;
import com.manage_staff.dto.request.CertificationUpdateRequest;
import com.manage_staff.dto.response.CertificationResponse;
import com.manage_staff.entity.Certification;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ICertificationService {

    List<CertificationResponse> findAll();
    List<CertificationResponse> findAllById( List<String> ids);

    Page<CertificationResponse> paging(String column, String value,
                                       int currentPage, int pageSize,
                                       String orderBy, String sortBy);

    CertificationResponse findById( String id);

    CertificationResponse save(CertificationRequest request);

    CertificationResponse update (String id, CertificationUpdateRequest request);

    void deleteById(String id);
    void deleteAllById(List<String> ids);
    void deleteAll();
}
