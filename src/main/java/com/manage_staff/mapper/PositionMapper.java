package com.manage_staff.mapper;

import com.manage_staff.dto.request.PermissionRequest;
import com.manage_staff.dto.request.PositionRequest;
import com.manage_staff.dto.response.PositionResponse;
import com.manage_staff.entity.Position;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PositionMapper {
    Position toPosition(PositionRequest request);
    PositionResponse toPositionResponse(Position position);
}
