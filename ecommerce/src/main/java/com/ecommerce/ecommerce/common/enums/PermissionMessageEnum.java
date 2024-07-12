package com.ecommerce.ecommerce.common.enums;

import org.springframework.http.HttpStatus;

public enum PermissionMessageEnum {
    CREATE_SUCCESS(20001, "Uncategorized Exception", HttpStatus.OK),
    FIND_ALL_SUCCESS(20001, "Uncategorized Exception", HttpStatus.OK),

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
