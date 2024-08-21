package com.manage_staff.service;


import com.manage_staff.dto.request.PermissionRequest;
import com.manage_staff.dto.request.PositionRequest;
import com.manage_staff.dto.response.LeaveDayResponse;
import com.manage_staff.dto.response.PositionResponse;

import java.util.List;

public interface IPositionService {
    List<PositionResponse> findAll();
    List<PositionResponse> findAllByNameLike(String name);
    PositionResponse findById(String id);

    PositionResponse save(PositionRequest request);

    void deleteById(String id);

    void deleteAllById(List<String> ids);

    void deleteAll();
}
