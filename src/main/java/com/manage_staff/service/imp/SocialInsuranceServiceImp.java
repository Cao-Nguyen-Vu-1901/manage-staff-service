package com.manage_staff.service.imp;

import com.manage_staff.dto.request.SocialInsuranceRequest;
import com.manage_staff.dto.response.SocialInsuranceResponse;
import com.manage_staff.entity.SocialInsurance;
import com.manage_staff.exception.AppException;
import com.manage_staff.exception.ErrorCode;
import com.manage_staff.mapper.SocialInsuranceMapper;
import com.manage_staff.repository.SocialInsuranceRepository;
import com.manage_staff.service.ISocialInsuranceService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class SocialInsuranceServiceImp implements ISocialInsuranceService {

    SocialInsuranceRepository socialInsuranceRepository;

    SocialInsuranceMapper socialInsuranceMapper;


    @Override
    public List<SocialInsuranceResponse> findAll() {
        return socialInsuranceRepository.findAll()
                .stream().map(socialInsuranceMapper::toSocialInsuranceResponse)
                .collect(Collectors.toList());
    }

    @Override
    public SocialInsuranceResponse findById(String id) {
        return socialInsuranceMapper
                .toSocialInsuranceResponse(socialInsuranceRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.SOCIAL_INSURANCE_NOT_EXISTED)));
    }

    @Override
    public SocialInsuranceResponse save(SocialInsuranceRequest request) {
        SocialInsurance socialInsurance = socialInsuranceMapper.toSocialInsurance(request);
        return socialInsuranceMapper
                .toSocialInsuranceResponse(socialInsuranceRepository.save(socialInsurance));
    }

    @Override
    public void deleteById(String id) {
        socialInsuranceRepository.deleteById(id);
    }

    @Override
    public void deleteAllById(List<String> ids) {
        socialInsuranceRepository.deleteAllById(ids);
    }

    @Override
    public void deleteAll() {
        socialInsuranceRepository.deleteAll();
    }
}
