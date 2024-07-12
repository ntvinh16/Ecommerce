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

    //Permission
    PERMISSION_EXISTED(2001, "Permission already existed", HttpStatus.BAD_REQUEST),
    PERMISSION_FALSE(2002, "Permission unsuccessful", HttpStatus.NOT_FOUND),
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
