package com.manage_staff.service.imp;

import com.manage_staff.dto.request.CertificationRequest;
import com.manage_staff.dto.response.CertificationResponse;
import com.manage_staff.entity.Certification;
import com.manage_staff.exception.AppException;
import com.manage_staff.exception.ErrorCode;
import com.manage_staff.mapper.CertificationMapper;
import com.manage_staff.repository.CertificationRepository;
import com.manage_staff.service.ICertificationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class CertificationServiceImp implements ICertificationService {

    CertificationRepository certificationRepository;

    CertificationMapper certificationMapper;

    @Override
    public List<CertificationResponse> findAll() {
        return certificationRepository.findAll()
                .stream().map(certificationMapper :: toCertificationResponse)
                .collect(Collectors.toList());
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
        return certificationMapper
                .toCertificationResponse(certificationRepository.save(certification));
    }

    @Override
    public void delete(String id) {
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
