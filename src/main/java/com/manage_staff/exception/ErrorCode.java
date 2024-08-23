package com.manage_staff.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {

    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(1001, "Uncategorized error", HttpStatus.BAD_REQUEST),
    USERNAME_INVALID(1003, "Username must be at least {min} characters", HttpStatus.BAD_REQUEST),
    INVALID_PASSWORD(1004, "Password must be at least {min} characters", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(1005, "User is not existed", HttpStatus.NOT_FOUND),
    UNAUTHENTICATED(1006, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1007, "You do not have permission", HttpStatus.FORBIDDEN),
    INVALID_DOB(1008, "Your age must be at least {min}", HttpStatus.BAD_REQUEST),

    BENEFIT_EXISTED(1100, "Benefit exited", HttpStatus.BAD_REQUEST),
    CERTIFICATION_EXISTED(1101, "Certification exited", HttpStatus.BAD_REQUEST) ,
    DEPARTMENT_EXISTED(1102, "Department exited", HttpStatus.BAD_REQUEST) ,
    LEAVE_DAY_EXISTED(1103, "Leave day exited", HttpStatus.BAD_REQUEST) ,
    PAYROLL_EXISTED(1104, "Payroll exited", HttpStatus.BAD_REQUEST) ,
    PERMISSION_EXISTED(1105, "Permission exited", HttpStatus.BAD_REQUEST) ,
    POSITION_EXISTED(1106, "Position exited", HttpStatus.BAD_REQUEST) ,
    REWARD_DISCIPLINE_EXISTED(1107, "Reward discipline exited", HttpStatus.BAD_REQUEST) ,
    ROLE_EXISTED(1108, "Role exited", HttpStatus.BAD_REQUEST) ,
    SOCIAL_INSURANCE_EXISTED(1109, "Social insurance exited", HttpStatus.BAD_REQUEST) ,
    STAFF_EXISTED(1110, "Staff exited", HttpStatus.BAD_REQUEST) ,

    BENEFIT_NOT_EXISTED(1201, "Benefit is not exited", HttpStatus.NOT_FOUND),
    CERTIFICATION_NOT_EXISTED(1202, "Certification is not exited", HttpStatus.NOT_FOUND) ,
    DEPARTMENT_NOT_EXISTED(1203, "Department is not exited", HttpStatus.NOT_FOUND) ,
    LEAVE_DAY_NOT_EXISTED(1204, "Leave day is not exited", HttpStatus.NOT_FOUND) ,
    PAYROLL_NOT_EXISTED(1205, "Payroll is not exited", HttpStatus.NOT_FOUND) ,
    PERMISSION_NOT_EXISTED(1206, "Permission is not exited", HttpStatus.NOT_FOUND) ,
    POSITION_NOT_EXISTED(1207, "Position is not exited", HttpStatus.NOT_FOUND) ,
    REWARD_DISCIPLINE_NOT_EXISTED(1208, "Reward discipline is not exited", HttpStatus.NOT_FOUND) ,
    ROLE_NOT_EXISTED(1209, "Role is not exited", HttpStatus.NOT_FOUND) ,
    SOCIAL_INSURANCE_NOT_EXISTED(1210, "Social insurance is not exited", HttpStatus.NOT_FOUND) ,
    STAFF_NOT_EXISTED(1211, "Staff is not exited", HttpStatus.NOT_FOUND) ,

    DATE_INVALID(1301, "Date invalid", HttpStatus.BAD_REQUEST) ,
    VALUE_INVALID(1301, "Value invalid", HttpStatus.BAD_REQUEST) ,

    STAFF_HAVE_SOCIAL_INSURANCE(1401, "Staff have social insurance", HttpStatus.INTERNAL_SERVER_ERROR) ,


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
