package com.manage_staff.exception;

import com.manage_staff.dto.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiResponse> handlingAppException(AppException exception){
        ErrorCode errorCode = exception.getErrorCode();
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage(errorCode.getMessage());
        apiResponse.setCode(errorCode.getCode());
        return ResponseEntity.status(errorCode.getStatus()).body(apiResponse);
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    ResponseEntity<ApiResponse> handlingHttpMessageNotReadableException(HttpMessageNotReadableException exception){

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                ApiResponse.builder()
                        .code(400)
                        .message(exception.getMessage()).build()
        );
    }

    @ExceptionHandler(value = SQLIntegrityConstraintViolationException.class)
    ResponseEntity<ApiResponse> handlingSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException exception){
        ErrorCode errorCode  = ErrorCode.VALUE_INVALID;
        if(exception.getMessage().contains("social_insurance")){
            errorCode = ErrorCode.STAFF_HAVE_SOCIAL_INSURANCE;
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.builder().code(errorCode.getCode())
                        .message(errorCode.getMessage())
                        .build());
    }


}
