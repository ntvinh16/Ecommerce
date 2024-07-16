package com.ecommerce.ecommerce.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Entity
@Table(name = "ROLE_PERMISSIONS_TBL")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RolePermissionEntity {
    @Id
    @Column(name = "ROLE_ID", nullable = false)
    UUID roleId;

    @Column(name = "PERMISSION_ID", nullable = false)
    UUID permissionId;
}
