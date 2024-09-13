package com.manage_staff.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.manage_staff.dto.request.CertificationRequest;
import com.manage_staff.dto.request.CertificationUpdateRequest;
import com.manage_staff.dto.response.CertificationResponse;

public interface ICertificationService {

    List<CertificationResponse> findAllById(List<String> ids);

    Page<CertificationResponse> paging(
            String column, String value, int currentPage, int pageSize, String orderBy, String sortBy);

    CertificationResponse findById(String id);

    CertificationResponse save(CertificationRequest request);

    CertificationResponse update(String id, CertificationUpdateRequest request, MultipartFile file)
            throws JsonProcessingException;

    void deleteById(String id);

    void deleteAllById(List<String> ids);

    void deleteAll();
}
