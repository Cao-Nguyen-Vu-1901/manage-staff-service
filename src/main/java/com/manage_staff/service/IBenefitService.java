package com.manage_staff.service;

import com.manage_staff.dto.request.BenefitRequest;
import com.manage_staff.dto.response.BenefitResponse;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IBenefitService {
    List<BenefitResponse> findAll();
    List<BenefitResponse> findAllById( List<String> ids);
    BenefitResponse findById(String id);

    BenefitResponse save(BenefitRequest benefitRequest);

    void deleteById(String id);
    void deleteAllById(List<String> ids);
    void deleteAll();

}
