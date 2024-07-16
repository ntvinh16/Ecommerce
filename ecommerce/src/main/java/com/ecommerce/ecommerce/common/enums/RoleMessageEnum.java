package com.ecommerce.ecommerce.common.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public enum RoleMessageEnum {
    CREATE_SUCCESS(20101, "Create role success", HttpStatus.OK),
    UPDATE_SUCCESS(20102, "Update role success", HttpStatus.OK),
    DELETE_SUCCESS(20103, "Delete role success", HttpStatus.OK),
    FIND_ALL_SUCCESS(20104, "Find all roles success", HttpStatus.OK),
;
    RoleMessageEnum(int code, String message, HttpStatus statusCode){
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    int code;
    String message;
    HttpStatus statusCode;
}
