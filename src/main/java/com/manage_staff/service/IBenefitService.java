package com.manage_staff.service;

import com.manage_staff.dto.request.BenefitRequest;
import com.manage_staff.dto.response.BenefitResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IBenefitService {
    List<BenefitResponse> findAll();
    List<BenefitResponse> findAllById( List<String> ids);
    Page<BenefitResponse> paging(String column, String value, int currentPage, int pageSize,
                                 String sortBy, String orderBy);
    BenefitResponse findById(String id);
    BenefitResponse save(BenefitRequest benefitRequest);
    BenefitResponse update(String id, BenefitRequest benefitRequest);
    void deleteById(String id);
    void deleteAllById(List<String> ids);
    void deleteAll();

}
