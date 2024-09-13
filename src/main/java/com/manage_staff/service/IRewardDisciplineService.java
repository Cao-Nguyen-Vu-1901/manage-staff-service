package com.manage_staff.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.manage_staff.dto.request.RewardDisciplineRequest;
import com.manage_staff.dto.response.RewardDisciplineResponse;

public interface IRewardDisciplineService {
    List<RewardDisciplineResponse> findAll();

    List<RewardDisciplineResponse> findAllById(List<String> ids);

    List<RewardDisciplineResponse> findAllByNameLike(String name);

    Page<RewardDisciplineResponse> paging(
            String column, String value, int currentPage, int pageSize, String orderBy, String sortBy);

    RewardDisciplineResponse findById(String id);

    RewardDisciplineResponse save(RewardDisciplineRequest request);

    RewardDisciplineResponse update(String id, RewardDisciplineRequest request);

    void deleteById(String id);

    void deleteAllById(List<String> ids);

    void deleteAll();
}
