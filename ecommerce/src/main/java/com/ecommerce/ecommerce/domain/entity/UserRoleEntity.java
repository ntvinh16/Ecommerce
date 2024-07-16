package com.ecommerce.ecommerce.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Entity
@Table(name = "USER_ROLES_TBL")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRoleEntity {
    @Id
    @Column(name = "USER_ID", nullable = false)
    UUID userId;

    @Column(name = "ROLE_ID", nullable = false)
    UUID roleId;
}
