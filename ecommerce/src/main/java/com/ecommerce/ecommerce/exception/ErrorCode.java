package com.ecommerce.ecommerce.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public enum ErrorCode {
    //App
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized Exception", HttpStatus.INTERNAL_SERVER_ERROR),
    UNAUTHENTICATED_EXCEPTION(1001, "Unauthenticated Exception", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED_EXCEPTION(1002, "You do not have permission", HttpStatus.FORBIDDEN),
    ID_NULL_EXCEPTION(1003, "Id do not empty", HttpStatus.BAD_REQUEST),
    INVALID_KEY(1004, "Invalid message key", HttpStatus.BAD_REQUEST),

    //Permission
    PERMISSION_EXISTED(2001, "Permission already existed", HttpStatus.BAD_REQUEST),
    PERMISSION_FALSE(2002, "Permission unsuccessful", HttpStatus.NOT_FOUND),
    PERMISSION_NOT_EXISTED(2002, "Permission not existed", HttpStatus.NOT_FOUND),


    //Role
    ROLE_EXISTED(2101, "Role already existed", HttpStatus.BAD_REQUEST),
    ROLE_FALSE(2102, "Role unsuccessful", HttpStatus.NOT_FOUND),
    ROLE_NOT_EXISTED(2102, "Role not existed", HttpStatus.NOT_FOUND),

    //User
    USER_EXISTED(2201, "User already existed", HttpStatus.BAD_REQUEST),
    USER_FALSE(2202, "User unsuccessful", HttpStatus.NOT_FOUND),
    USER_NOT_EXISTED(2203, "User not existed", HttpStatus.NOT_FOUND),
    EMAIL_EXISTED(2204, "Email already existed", HttpStatus.NOT_FOUND),
    USERNAME_INVALID(2205, "Username must be ad least {min} characters and at most 20 characters", HttpStatus.BAD_REQUEST),
    PASSWORD_INVALID(2206, "Password must be ad least {min} characters and at most 20 characters", HttpStatus.BAD_REQUEST),
    EMAIL_ERROR(2207, "Email malformed", HttpStatus.BAD_REQUEST),
    USERNAME_NOT_EMPTY(2208, "Username don't empty", HttpStatus.BAD_REQUEST),
    PASSWORD_NOT_EMPTY(2209, "Password don't empty", HttpStatus.BAD_REQUEST),
    EMAIL_NOT_EMPTY(22010, "Email don't empty", HttpStatus.BAD_REQUEST),
    ROLES_NOT_EMPTY(22011, "Roles don't empty", HttpStatus.BAD_REQUEST),
    INVALID_DOB(22012, "Your age must be at least {min}", HttpStatus.BAD_REQUEST),
    ;

    ErrorCode(int code, String message, HttpStatus statusCode){
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    int code;
    String message;
    HttpStatus statusCode;
}
