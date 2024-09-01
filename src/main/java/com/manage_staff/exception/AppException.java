package com.manage_staff.exception;

import lombok.*;
import lombok.experimental.FieldDefaults;


@Getter
@Setter
public class AppException extends RuntimeException{
    private ErrorCode errorCode;

    public AppException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
