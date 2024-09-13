package com.manage_staff.service.imp;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.manage_staff.dao.LeaveDayDAO;
import com.manage_staff.dto.request.LeaveDayRequest;
import com.manage_staff.dto.response.LeaveDayResponse;
import com.manage_staff.entity.LeaveDay;
import com.manage_staff.exception.AppException;
import com.manage_staff.exception.ErrorCode;
import com.manage_staff.mapper.LeaveDayMapper;
import com.manage_staff.repository.LeaveDayRepository;
import com.manage_staff.service.ILeaveDayService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class LeaveDayServiceImp implements ILeaveDayService {

    LeaveDayRepository leaveDayRepository;
    LeaveDayMapper leaveDayMapper;
    LeaveDayDAO leaveDayDAO;

    @Override
    public List<LeaveDayResponse> findAll() {
        return leaveDayRepository.findAll().stream()
                .map(leaveDayMapper::toLeaveDayResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<LeaveDayResponse> findAllById(List<String> ids) {
        return leaveDayRepository.findAllById(ids).stream()
                .map(leaveDayMapper::toLeaveDayResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<LeaveDayResponse> findAllByNameLike(String name) {
        return leaveDayRepository.findAllByNameLike("%" + name + "%").stream()
                .map(leaveDayMapper::toLeaveDayResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Page<LeaveDayResponse> paging(
            String column, String value, int currentPage, int pageSize, String orderBy, String sortBy) {
        return leaveDayDAO
                .paging(column, value, currentPage, pageSize, orderBy, sortBy)
                .map(leaveDayMapper::toLeaveDayResponse);
    }

    @Override
    public LeaveDayResponse findById(String id) {
        return leaveDayMapper.toLeaveDayResponse(
                leaveDayRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.LEAVE_DAY_NOT_EXISTED)));
    }

    @Override
    public LeaveDayResponse save(LeaveDayRequest request) {
        LeaveDay leaveDay = leaveDayMapper.toLeaveDay(request);
        return leaveDayMapper.toLeaveDayResponse(leaveDayRepository.save(leaveDay));
    }

    @Override
    public LeaveDayResponse update(String id, LeaveDayRequest request) {
        LeaveDay leaveDay =
                leaveDayRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.LEAVE_DAY_NOT_EXISTED));
        leaveDayMapper.updateLeaveDay(leaveDay, request);
        return leaveDayMapper.toLeaveDayResponse(leaveDayRepository.save(leaveDay));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public void deleteById(String id) {
        leaveDayRepository.deleteById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public void deleteAllById(List<String> ids) {
        leaveDayRepository.deleteAllById(ids);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public void deleteAll() {
        leaveDayRepository.deleteAll();
    }
}
