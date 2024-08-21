package com.manage_staff.service.imp;

import com.manage_staff.dto.request.PermissionRequest;
import com.manage_staff.dto.request.PositionRequest;
import com.manage_staff.dto.response.PositionResponse;
import com.manage_staff.entity.Position;
import com.manage_staff.exception.AppException;
import com.manage_staff.exception.ErrorCode;
import com.manage_staff.mapper.PositionMapper;
import com.manage_staff.repository.PositionRepository;
import com.manage_staff.service.IPositionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class PositionServiceImp implements IPositionService {

    PositionMapper positionMapper;
    PositionRepository positionRepository;
    @Override
    public List<PositionResponse> findAll() {
        return positionRepository.findAll()
                .stream().map(positionMapper::toPositionResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<PositionResponse> findAllByNameLike(String name) {
        return positionRepository.findAllByNameLike("%" + name + "%")
                .stream().map(positionMapper::toPositionResponse)
                .collect(Collectors.toList());
    }

    @Override
    public PositionResponse findById(String id) {
        return positionMapper
                .toPositionResponse(positionRepository.findById(id)
                        .orElseThrow(() -> new AppException(ErrorCode.POSITION_NOT_EXISTED)));
    }

    @Override
    public PositionResponse save(PositionRequest request) {
        Position position = positionMapper.toPosition(request);
        return positionMapper.toPositionResponse(positionRepository.save(position));
    }

    @Override
    public void deleteById(String id) {
        positionRepository.deleteById(id);
    }

    @Override
    public void deleteAllById(List<String> ids) {
        positionRepository.deleteAllById(ids);
    }

    @Override
    public void deleteAll() {
        positionRepository.deleteAll();
    }
}
