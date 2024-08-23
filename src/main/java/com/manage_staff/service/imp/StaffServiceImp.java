package com.manage_staff.service.imp;

import com.manage_staff.dto.request.StaffRequest;
import com.manage_staff.dto.response.StaffResponse;
import com.manage_staff.entity.Staff;
import com.manage_staff.exception.AppException;
import com.manage_staff.exception.ErrorCode;
import com.manage_staff.mapper.PositionMapper;
import com.manage_staff.mapper.StaffMapper;
import com.manage_staff.repository.*;
import com.manage_staff.service.IStaffService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class StaffServiceImp implements IStaffService {

    StaffRepository staffRepository;
    StaffMapper staffMapper;
    CertificationRepository certificationRepository;
    SocialInsuranceRepository socialInsuranceRepository;
    RewardDisciplineRepository rewardDisciplineRepository;
    LeaveDayRepository leaveDayRepository;
    BenefitRepository benefitRepository;
    RoleRepository roleRepository;

    @Override
    public List<StaffResponse> findAll() {
        return staffRepository.findAll().stream().map(staffMapper::toStaffResponse).collect(Collectors.toList());
    }

    @Override
    public List<StaffResponse> findAllById(List<String> ids) {
        return staffRepository.findAllById(ids)
                .stream().map(staffMapper::toStaffResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<StaffResponse> findAllByNameLike(String name) {
        return staffRepository.findAllByNameLike("%" + name + "%")
                .stream().map(staffMapper::toStaffResponse)
                .collect(Collectors.toList());
    }

    @Override
    public StaffResponse findById(String id) {
        return staffMapper.toStaffResponse(
                staffRepository.findById(id)
                        .orElseThrow( () -> new AppException(ErrorCode.STAFF_NOT_EXISTED)));
    }

    @Override
    public StaffResponse save(StaffRequest request) {

        if(staffRepository.findByUsername(request.getUsername()) != null){
            throw new AppException(ErrorCode.STAFF_EXISTED);
        }

        Staff staff = staffMapper.toStaff(request);

        if(request.getCertifications() != null){
            var certificationIds = request.getCertifications();
            var certifications = certificationRepository.findAllById(certificationIds);
            staff.setCertifications(certifications);
        }

        if(request.getSocialInsurance() != null){
            var socialInsuranceId = request.getSocialInsurance();
            var socialInsurance = socialInsuranceRepository.findById(socialInsuranceId)
                    .orElseThrow( () -> new AppException(ErrorCode.SOCIAL_INSURANCE_NOT_EXISTED));
            staff.setSocialInsurance(socialInsurance);
        }

        if(request.getRewardDisciplines() != null){
            var rewardDisciplineIds = request.getRewardDisciplines();
            var rewardDisciplines = rewardDisciplineRepository.findAllById(rewardDisciplineIds);
            staff.setRewardDisciplines(rewardDisciplines);
        }

        if(request.getLeaves() != null){
            var leaveDayIds = request.getLeaves();
            var leaveDays = leaveDayRepository.findAllById(leaveDayIds);
            staff.setLeaves(leaveDays);
        }

        if(request.getBenefits() != null){
            var benefitIds = request.getBenefits();
            var benefits = benefitRepository.findAllById(benefitIds);
            staff.setBenefits(benefits);
        }

        if(request.getRoles() != null){
            var roleIds = request.getRoles();
            var roles = roleRepository.findAllById(roleIds);
            staff.setRoles(new HashSet<>(roles));
        }

        return staffMapper.toStaffResponse(staffRepository.save(staff));
    }

    @Override
    public void deleteById(String id) {
        staffRepository.findById(id);
    }

    @Override
    public void deleteAllById(List<String> ids) {
        staffRepository.deleteAllById(ids);
    }

    @Override
    public void deleteAll() {
        staffRepository.deleteAll();
    }
}
