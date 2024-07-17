package com.ecommerce.ecommerce.common.enums.roles;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public enum PermissionEnum {
    APPROVE_POST(1, "APPROVE_POST"),
    CREATE_POST(2, "CREATE_POST"),
    UPDATE_POST(3, "UPDATE_POST");
    ;

    PermissionEnum(int code, String message){
        this.code = code;
        this.message = message;
    }

    final int code;
    final String message;
}