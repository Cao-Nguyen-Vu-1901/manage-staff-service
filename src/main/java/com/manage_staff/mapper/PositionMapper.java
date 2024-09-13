package com.manage_staff.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import com.manage_staff.dto.request.PositionRequest;
import com.manage_staff.dto.request.PositionUpdateRequest;
import com.manage_staff.dto.response.PositionResponse;
import com.manage_staff.dto.response.StaffResponse;
import com.manage_staff.entity.Position;
import com.manage_staff.entity.Staff;

@Mapper(componentModel = "spring")
public interface PositionMapper {

    @Mapping(target = "staff", ignore = true)
    @Mapping(target = "payroll", ignore = true)
    @Mapping(target = "department", ignore = true)
    Position toPosition(PositionRequest request);

    @Mapping(target = "payroll.positions", ignore = true)
    @Mapping(target = "department.positions", ignore = true)
    @Mapping(target = "staff", source = "staff", qualifiedByName = "mapStaff")
    PositionResponse toPositionResponse(Position position);

    @Named("mapStaff")
    default List<StaffResponse> staffListToStaffResponseList(List<Staff> staff) {
        if (staff == null) {
            return new ArrayList<>();
        }
        return staff.stream().map(this::staffToStaffResponse).collect(Collectors.toList());
    }

    @Mapping(target = "certifications", ignore = true)
    @Mapping(target = "socialInsurance", ignore = true)
    StaffResponse staffToStaffResponse(Staff staff);

    @Mapping(target = "payroll", ignore = true)
    @Mapping(target = "department", ignore = true)
    void updatePosition(@MappingTarget Position position, PositionUpdateRequest request);
}
