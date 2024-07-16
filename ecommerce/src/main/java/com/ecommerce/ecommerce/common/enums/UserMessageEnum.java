package com.ecommerce.ecommerce.common.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public enum UserMessageEnum {
    CREATE_SUCCESS(20201, "Create user success", HttpStatus.OK),
    UPDATE_SUCCESS(20202, "Update user success", HttpStatus.OK),
    DELETE_SUCCESS(20203, "Delete user success", HttpStatus.OK),
    FIND_ALL_SUCCESS(20204, "Find all users success", HttpStatus.OK),
    FIND_USER_SUCCESS(20205, "Find users success", HttpStatus.OK),
;
    UserMessageEnum(int code, String message, HttpStatus statusCode){
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    int code;
    String message;
    HttpStatus statusCode;
}
