package com.manage_staff.service.imp;

import com.manage_staff.dto.request.BenefitRequest;
import com.manage_staff.dto.response.BenefitResponse;
import com.manage_staff.entity.Benefit;
import com.manage_staff.exception.AppException;
import com.manage_staff.exception.ErrorCode;
import com.manage_staff.mapper.BenefitMapper;
import com.manage_staff.repository.BenefitRepository;
import com.manage_staff.service.IBenefitService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class BenefitServiceImp implements IBenefitService {

    BenefitRepository benefitRepository;
    BenefitMapper benefitMapper;

    @Override
    public List<BenefitResponse> findAll() {
        return benefitRepository.findAll()
                .stream().map(benefitMapper :: toBenefitResponse)
                .collect(Collectors.toList());
    }

    @Override
    public BenefitResponse findById(String id) {
        return benefitMapper.
                toBenefitResponse(benefitRepository.findById(id)
                        .orElseThrow(() -> new AppException(ErrorCode.BENEFIT_NOT_EXISTED)));
    }

    @Override
    public BenefitResponse save(BenefitRequest benefitRequest) {
        Benefit benefit = benefitMapper.toBenefit(benefitRequest);
        return benefitMapper.toBenefitResponse(benefitRepository.save(benefit));
    }

    @Override
    public void delete(String id) {
        benefitRepository.deleteById(id);
    }

    @Override
    public void deleteAllById(List<String> ids) {
        benefitRepository.deleteAllById(ids);
    }

    @Override
    public void deleteAll() {
        benefitRepository.deleteAll();
    }
}
