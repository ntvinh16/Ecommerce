package com.ecommerce.ecommerce.common.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum AuthenticationEnum {
    LOGIN_SUCCESS(20301, "Login success",HttpStatus.OK),
    INTROSPECT_SUCCESS(20302, "Introspect success", HttpStatus.OK),
    DELETE_SUCCESS(20303, "Delete user success", HttpStatus.OK),
    FIND_ALL_SUCCESS(20304, "Find all users success", HttpStatus.OK),
    FIND_USER_SUCCESS(20305, "Find users success", HttpStatus.OK),
            ;
    AuthenticationEnum(int code, String message, HttpStatus statusCode){
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    int code;
    String message;
    HttpStatus statusCode;
}
