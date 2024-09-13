package com.manage_staff.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import com.manage_staff.dto.request.DepartmentRequest;
import com.manage_staff.dto.response.DepartmentResponse;
import com.manage_staff.dto.response.PositionResponse;
import com.manage_staff.entity.Department;
import com.manage_staff.entity.Position;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {

    @Mapping(target = "positions", ignore = true)
    Department toDepartment(DepartmentRequest request);

    @Mapping(target = "positions", source = "positions", qualifiedByName = "mapPositions")
    DepartmentResponse toDepartmentResponse(Department department);

    @Named("mapPositions")
    default List<PositionResponse> positionResponseListToPositionResponseList(List<Position> positions) {
        if (positions == null) return new ArrayList<>();
        return positions.stream().map(this::positionToPositionResponse).collect(Collectors.toList());
    }

    @Mapping(target = "staff", ignore = true)
    @Mapping(target = "department", ignore = true)
    @Mapping(target = "payroll.positions", ignore = true)
    PositionResponse positionToPositionResponse(Position position);

    void updateDepartment(@MappingTarget Department department, DepartmentRequest request);
}
