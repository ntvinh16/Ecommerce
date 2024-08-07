package com.ecommerce.ecommerce.common.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public enum PermissionMessageEnum {
    CREATE_SUCCESS(20001, "Create permission success", HttpStatus.OK),
    FIND_ALL_SUCCESS(20002, "Get all permission", HttpStatus.OK),
    DELETE_SUCCESS(20003, "Delete permission success", HttpStatus.OK),

    ;

    PermissionMessageEnum(int code, String message, HttpStatus statusCode){
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    int code;
    String message;
    HttpStatus statusCode;
}
