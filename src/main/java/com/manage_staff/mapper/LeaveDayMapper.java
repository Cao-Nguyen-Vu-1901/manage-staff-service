package com.manage_staff.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.manage_staff.dto.request.LeaveDayRequest;
import com.manage_staff.dto.response.LeaveDayResponse;
import com.manage_staff.entity.LeaveDay;

@Mapper(componentModel = "spring")
public interface LeaveDayMapper {

    LeaveDay toLeaveDay(LeaveDayRequest request);

    LeaveDayResponse toLeaveDayResponse(LeaveDay leaveDay);

    void updateLeaveDay(@MappingTarget LeaveDay leaveDay, LeaveDayRequest request);
}
