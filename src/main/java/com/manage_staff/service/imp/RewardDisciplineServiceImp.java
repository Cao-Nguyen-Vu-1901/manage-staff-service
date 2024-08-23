package com.manage_staff.service.imp;

import com.manage_staff.dto.request.RewardDisciplineRequest;
import com.manage_staff.dto.response.RewardDisciplineResponse;
import com.manage_staff.entity.RewardDiscipline;
import com.manage_staff.exception.AppException;
import com.manage_staff.exception.ErrorCode;
import com.manage_staff.mapper.RewardDisciplineMapper;
import com.manage_staff.repository.RewardDisciplineRepository;
import com.manage_staff.service.IRewardDisciplineService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.rmi.Remote;
import java.util.List;
import java.util.stream.Collectors;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class RewardDisciplineServiceImp  implements IRewardDisciplineService {

    RewardDisciplineRepository rewardDisciplineRepository;
    RewardDisciplineMapper rewardDisciplineMapper;
    @Override
    public List<RewardDisciplineResponse> findAll() {
        return rewardDisciplineRepository.findAll()
                .stream().map(rewardDisciplineMapper::toRewardDisciplineResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<RewardDisciplineResponse> findAllById(List<String> ids) {
        return rewardDisciplineRepository.findAllById(ids)
                .stream().map(rewardDisciplineMapper::toRewardDisciplineResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<RewardDisciplineResponse> findAllByNameLike(String name) {
        return rewardDisciplineRepository.findAllByNameLike("%" + name + "%")
                .stream().map(rewardDisciplineMapper::toRewardDisciplineResponse)
                .collect(Collectors.toList());
    }

    @Override
    public RewardDisciplineResponse findById(String id) {
        return rewardDisciplineMapper.toRewardDisciplineResponse(rewardDisciplineRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.REWARD_DISCIPLINE_NOT_EXISTED)));
    }

    @Override
    public RewardDisciplineResponse save(RewardDisciplineRequest request) {
        RewardDiscipline rewardDiscipline = rewardDisciplineMapper.toRewardDiscipline(request);
        return rewardDisciplineMapper.toRewardDisciplineResponse(rewardDisciplineRepository.save(rewardDiscipline));
    }

    @Override
    public void deleteById(String id) {
        rewardDisciplineRepository.deleteById(id);
    }

    @Override
    public void deleteAllById(List<String> ids) {
        rewardDisciplineRepository.deleteAllById(ids);
    }

    @Override
    public void deleteAll() {
        rewardDisciplineRepository.deleteAll();
    }
}
