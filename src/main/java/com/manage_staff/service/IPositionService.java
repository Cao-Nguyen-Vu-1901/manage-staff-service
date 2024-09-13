package com.manage_staff.service;

import java.util.List;

import com.manage_staff.dto.request.PositionRequest;
import com.manage_staff.dto.request.PositionUpdateRequest;
import com.manage_staff.dto.response.PositionResponse;

public interface IPositionService {
    List<PositionResponse> findAll();

    List<PositionResponse> findAllById(List<String> ids);

    List<PositionResponse> findAllByNameLike(String name);

    PositionResponse findById(String id);

    PositionResponse save(PositionRequest request);

    PositionResponse update(String id, PositionUpdateRequest request);

    void deleteById(String id);

    void deleteAllById(List<String> ids);

    void deleteAll();
}
