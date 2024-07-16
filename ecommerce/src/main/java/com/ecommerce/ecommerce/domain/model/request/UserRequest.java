package com.ecommerce.ecommerce.domain.model.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Date;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRequest {

    @Size(min = 4, max = 20, message = "USERNAME_INVALID")
    @NotEmpty(message = "USERNAME_NOT_EMPTY")
    String username;

    @Email(message = "EMAIL_ERROR", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    @NotEmpty(message = "EMAIL_NOT_EMPTY")
    String email;

    @Size(min = 8, max = 20, message = "PASSWORD_INVALID")
    @NotEmpty(message = "PASSWORD_NOT_EMPTY")
    String password;

    Date birthday;

    String fullName;

    @NotEmpty(message = "ROLES_NOT_EMPTY")
    Set<UUID> roles;
}
