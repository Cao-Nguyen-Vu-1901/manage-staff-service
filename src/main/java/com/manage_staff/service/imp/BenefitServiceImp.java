package com.manage_staff.service.imp;

import com.manage_staff.dao.BenefitDAO;
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
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class BenefitServiceImp implements IBenefitService {

    BenefitRepository benefitRepository;
    BenefitMapper benefitMapper;
    BenefitDAO benefitDAO;


    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public List<BenefitResponse> findAll() {
        return benefitRepository.findAll()
                .stream().map(benefitMapper :: toBenefitResponse)
                .collect(Collectors.toList());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public List<BenefitResponse> findAllById(List<String> ids) {
        return benefitRepository.findAllById(ids)
                .stream().map(benefitMapper::toBenefitResponse)
                .collect(Collectors.toList());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public BenefitResponse findById(String id) {
        return benefitMapper.
                toBenefitResponse(benefitRepository.findById(id)
                        .orElseThrow(() -> new AppException(ErrorCode.BENEFIT_NOT_EXISTED)));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public BenefitResponse save(BenefitRequest benefitRequest) {
        Benefit benefit = benefitMapper.toBenefit(benefitRequest);
        return benefitMapper.toBenefitResponse(benefitRepository.save(benefit));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public BenefitResponse update(String id, BenefitRequest benefitRequest) {
        Benefit benefit = benefitRepository.findById(id)
                .orElseThrow( () -> new AppException(ErrorCode.BENEFIT_NOT_EXISTED));
        benefitMapper.updateBenefit(benefit, benefitRequest);
        return benefitMapper.toBenefitResponse(benefitRepository.save(benefit));
    }

    @Override
    public Page<BenefitResponse> paging(String column, String value, int currentPage,
                                        int pageSize, String orderBy, String sortBy) {
        return benefitDAO.paging(column, value, currentPage, pageSize, orderBy, sortBy)
                .map(benefitMapper::toBenefitResponse);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public void deleteById(String id) {
        benefitRepository.deleteById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public void deleteAllById(List<String> ids) {
        benefitRepository.deleteAllById(ids);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public void deleteAll() {
        benefitRepository.deleteAll();
    }
}
