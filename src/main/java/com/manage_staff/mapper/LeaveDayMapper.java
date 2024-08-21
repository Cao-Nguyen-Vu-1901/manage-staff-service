package com.manage_staff.mapper;

import com.manage_staff.dto.request.LeaveDayRequest;
import com.manage_staff.dto.response.LeaveDayResponse;
import com.manage_staff.entity.LeaveDay;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LeaveDayMapper {

    LeaveDay toLeaveDay (LeaveDayRequest request);
    LeaveDayResponse toLeaveDayResponse (LeaveDay leaveDay);
}
