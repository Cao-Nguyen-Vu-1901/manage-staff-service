package com.manage_staff.controller.api;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.manage_staff.dto.request.CertificationRequest;
import com.manage_staff.dto.request.CertificationUpdateRequest;
import com.manage_staff.dto.response.*;
import com.manage_staff.service.ICertificationService;
import com.manage_staff.util.ProcessImage;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/api/certification")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class CertificationController {

    ICertificationService certificationService;

    @GetMapping
    public PagingResponse<List<CertificationResponse>> getAll(
            @RequestParam(defaultValue = "1") int currentPage,
            @RequestParam(defaultValue = "9") int pageSize,
            String type,
            String value,
            String sortBy,
            String orderBy) {
        Page<CertificationResponse> certificationResponses =
                certificationService.paging(type, value, currentPage, pageSize, orderBy, sortBy);

        return PagingResponse.<List<CertificationResponse>>builder()
                .code(1000)
                .currentPage(currentPage)
                .pageSize(pageSize)
                .sortBy(sortBy)
                .totalPage(certificationResponses.getTotalPages())
                .totalItem(certificationResponses.getTotalElements())
                .orderBy(orderBy)
                .type(type)
                .value(value)
                .result(certificationResponses.getContent())
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<CertificationResponse> getById(@PathVariable String id) {
        return ApiResponse.<CertificationResponse>builder()
                .result(certificationService.findById(id))
                .build();
    }

    @PostMapping
    public ApiResponse<CertificationResponse> create(
            @RequestParam(value = "file") MultipartFile file, CertificationRequest request) throws IOException {
        String filePath = ProcessImage.upload(file, "certifications/");
        request.setImage(filePath);
        var response = certificationService.save(request);

        return ApiResponse.<CertificationResponse>builder().result(response).build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> delete(@PathVariable String id) {
        certificationService.deleteById(id);
        return ApiResponse.<String>builder()
                .result("Permission has been delete")
                .build();
    }

    @PutMapping
    public ApiResponse<CertificationResponse> update(
            String id,
            CertificationUpdateRequest request,
            @RequestParam(value = "file", required = false) MultipartFile file)
            throws JsonProcessingException {

        return ApiResponse.<CertificationResponse>builder()
                .result(certificationService.update(id, request, file))
                .build();
    }
}
