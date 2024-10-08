package com.manage_staff.service;


import com.manage_staff.dto.request.SocialInsuranceRequest;
import com.manage_staff.dto.request.SocialInsuranceUpdateRequest;
import com.manage_staff.dto.response.LeaveDayResponse;
import com.manage_staff.dto.response.SocialInsuranceResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ISocialInsuranceService {
    List<SocialInsuranceResponse> findAll();
    SocialInsuranceResponse findById(String id);

    SocialInsuranceResponse save(SocialInsuranceRequest request);

    SocialInsuranceResponse update(String id, SocialInsuranceUpdateRequest request);

    void deleteById(String id);

    void deleteAllById(List<String> ids);

    void deleteAll();

    Page<SocialInsuranceResponse> paging(String column, String value,
                                         int currentPage, int pageSize,
                                         String orderBy, String sortBy);

}
