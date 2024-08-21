package com.manage_staff.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {

    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(1001, "Uncategorized error", HttpStatus.BAD_REQUEST),
    USER_EXISTED(1002, "User existed", HttpStatus.BAD_REQUEST),
    USERNAME_INVALID(1003, "Username must be at least {min} characters", HttpStatus.BAD_REQUEST),
    INVALID_PASSWORD(1004, "Password must be at least {min} characters", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(1005, "User is not existed", HttpStatus.NOT_FOUND),
    UNAUTHENTICATED(1006, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1007, "You do not have permission", HttpStatus.FORBIDDEN),
    INVALID_DOB(1008, "Your age must be at least {min}", HttpStatus.BAD_REQUEST),
    BENEFIT_NOT_EXISTED(1009, "Benefit is not exited", HttpStatus.NOT_FOUND),
    CERTIFICATION_NOT_EXISTED(1009, "Certification is not exited", HttpStatus.NOT_FOUND) ,
    DEPARTMENT_NOT_EXISTED(1009, "Department is not exited", HttpStatus.NOT_FOUND) ,
    LEAVE_DAY_NOT_EXISTED(1009, "Leave day is not exited", HttpStatus.NOT_FOUND) ,
    PAYROLL_NOT_EXISTED(1009, "Payroll is not exited", HttpStatus.NOT_FOUND) ,
    PERMISSION_NOT_EXISTED(1009, "Permission is not exited", HttpStatus.NOT_FOUND) ,
    POSITION_NOT_EXISTED(1009, "Position is not exited", HttpStatus.NOT_FOUND) ,
    REWARD_DISCIPLINE_NOT_EXISTED(1009, "Reward discipline is not exited", HttpStatus.NOT_FOUND) ,
    ROLE_NOT_EXISTED(1009, "Role is not exited", HttpStatus.NOT_FOUND) ,
    SOCIAL_INSURANCE_NOT_EXISTED(1009, "Social insurance is not exited", HttpStatus.NOT_FOUND) ,
    STAFF_NOT_EXISTED(1009, "Staff is not exited", HttpStatus.NOT_FOUND) ,
    ;

    ErrorCode(int code, String message, HttpStatusCode status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }

    private final int code;
    private final String message;
    private final HttpStatusCode status;
}
