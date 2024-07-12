package com.ecommerce.ecommerce.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Entity
@Table(name = "ROLES_PERMISSION_TBL")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RolePermissionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID")
    UUID id;

    @Column(name = "ROLE_ID", nullable = false)
    String roleId;

    @Column(name = "PERMISSION_ID", nullable = false)
    String permissionId;
}
