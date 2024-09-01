package com.manage_staff.service.imp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import com.manage_staff.util.ProcessImage;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class CertificationServiceImp implements ICertificationService {

    CertificationRepository certificationRepository;

    CertificationMapper certificationMapper;

    StaffRepository staffRepository;

    ObjectMapper objectMapper;

    @Override
    public List<CertificationResponse> findAll() {
        return certificationRepository.findAll()
                .stream().map(certificationMapper :: toCertificationResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<CertificationResponse> findAllById(List<String> ids) {
        return certificationRepository.findAllById(ids)
                .stream().map(certificationMapper::toCertificationResponse)
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
        String staffId = request.getStaff();
        var staff = staffRepository.findById(staffId).orElseThrow(() -> new AppException(ErrorCode.STAFF_NOT_EXISTED));
        certification.setStaff(staff);
        return certificationMapper
                .toCertificationResponse(certificationRepository.save(certification));
    }

    @Override
    public CertificationResponse update(String id, String request, MultipartFile file) throws JsonProcessingException {

        Certification certification = certificationRepository.findById(id)
                .orElseThrow( () -> new AppException(ErrorCode.CERTIFICATION_NOT_EXISTED) );

        CertificationUpdateRequest updateRequest = objectMapper.readValue(request, CertificationUpdateRequest.class);

        certificationMapper.updateCertification(certification,updateRequest);
        try {
            if(file != null){
                String imageName = ProcessImage.upload(file, "certifications/");
                if(imageName != null){
                    certification.setImage(imageName);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

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
