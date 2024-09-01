package com.manage_staff.service.imp;

import com.manage_staff.dao.SocialInsuranceDAO;
import com.manage_staff.dto.request.SocialInsuranceRequest;
import com.manage_staff.dto.request.SocialInsuranceUpdateRequest;
import com.manage_staff.dto.response.SocialInsuranceResponse;
import com.manage_staff.entity.SocialInsurance;
import com.manage_staff.exception.AppException;
import com.manage_staff.exception.ErrorCode;
import com.manage_staff.mapper.SocialInsuranceMapper;
import com.manage_staff.repository.SocialInsuranceRepository;
import com.manage_staff.repository.StaffRepository;
import com.manage_staff.service.ISocialInsuranceService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class SocialInsuranceServiceImp implements ISocialInsuranceService {

    SocialInsuranceRepository socialInsuranceRepository;

    SocialInsuranceMapper socialInsuranceMapper;
    StaffRepository staffRepository;
    SocialInsuranceDAO socialInsuranceDAO;

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

        var staff = staffRepository.findById(request.getStaff())
                .orElseThrow( () -> new AppException(ErrorCode.STAFF_NOT_EXISTED) );
        socialInsurance.setStaff(staff);

        try{
            return socialInsuranceMapper
                    .toSocialInsuranceResponse(socialInsuranceRepository.save(socialInsurance));
        }catch (AppException e){
            throw new AppException(ErrorCode.STAFF_HAVE_SOCIAL_INSURANCE);
        }


    }

    @Override
    public SocialInsuranceResponse update(String id, SocialInsuranceUpdateRequest request) {
        SocialInsurance socialInsurance = socialInsuranceRepository.findById(id)
                .orElseThrow( () -> new AppException(ErrorCode.SOCIAL_INSURANCE_NOT_EXISTED));
        socialInsuranceMapper.updateSocialInsurance(socialInsurance,request);

        return socialInsuranceMapper.toSocialInsuranceResponse(socialInsuranceRepository.save(socialInsurance));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public void deleteById(String id) {
        socialInsuranceRepository.deleteById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public void deleteAllById(List<String> ids) {
        socialInsuranceRepository.deleteAllById(ids);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public void deleteAll() {
        socialInsuranceRepository.deleteAll();
    }

    @Override
    public Page<SocialInsuranceResponse> paging(String column, String value, int currentPage,
                                                int pageSize, String orderBy, String sortBy) {
        return socialInsuranceDAO.paging(column, value, currentPage, pageSize, orderBy, sortBy)
                .map(socialInsuranceMapper::toSocialInsuranceResponse);
    }
}
