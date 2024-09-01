package com.manage_staff.service.imp;

import com.manage_staff.dao.CertificationDAO;
import com.manage_staff.dto.request.CertificationRequest;
import com.manage_staff.dto.request.CertificationUpdateRequest;
import com.manage_staff.dto.response.CertificationResponse;
import com.manage_staff.entity.Certification;
import com.manage_staff.entity.Staff;
import com.manage_staff.exception.AppException;
import com.manage_staff.exception.ErrorCode;
import com.manage_staff.mapper.CertificationMapper;
import com.manage_staff.mapper.StaffMapper;
import com.manage_staff.repository.CertificationRepository;
import com.manage_staff.repository.StaffRepository;
import com.manage_staff.service.ICertificationService;
import com.manage_staff.service.IStaffService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class CertificationServiceImp implements ICertificationService {

    CertificationRepository certificationRepository;

    CertificationMapper certificationMapper;

    StaffRepository staffRepository;
    CertificationDAO certificationDAO;

    @Override
    public List<CertificationResponse> findAll() {
        return certificationRepository.findAll()
                .stream().map(certificationMapper::toCertificationResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<CertificationResponse> findAllById(List<String> ids) {
        return certificationRepository.findAllById(ids)
                .stream().map(certificationMapper::toCertificationResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Page<CertificationResponse> paging(String column, String value, int currentPage, int pageSize, String orderBy, String sortBy) {
        return certificationDAO.paging(column, value, currentPage, pageSize, orderBy, sortBy)
                .map(certificationMapper :: toCertificationResponse);
    }

    @Override
    public CertificationResponse findById(String id) {
        return certificationMapper.toCertificationResponse(certificationRepository
                .findById(id).
                orElseThrow(() -> new AppException(ErrorCode.CERTIFICATION_NOT_EXISTED)));
    }

    @Override
    public CertificationResponse save(CertificationRequest request) {
        Certification certification = certificationMapper.toCertification(request);
        String staffId = request.getStaff();
        var staff = staffRepository.findById(staffId).orElseThrow(() -> new AppException(ErrorCode.STAFF_NOT_EXISTED));
        certification.setStaff(staff);
        return certificationMapper
                .toCertificationResponse(certificationRepository.save(certification));
    }

    @Override
    public CertificationResponse update(String id, CertificationUpdateRequest request) {

        Certification certification = certificationRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.CERTIFICATION_NOT_EXISTED));

        certificationMapper.updateCertification(certification, request);


        return certificationMapper.toCertificationResponse(certificationRepository.save(certification));
    }

    @Override
    public void deleteById(String id) {
        certificationRepository.deleteById(id);
    }

    @Override
    public void deleteAllById(List<String> ids) {
        certificationRepository.deleteAllById(ids);
    }

    @Override
    public void deleteAll() {
        certificationRepository.deleteAll();
    }
}
