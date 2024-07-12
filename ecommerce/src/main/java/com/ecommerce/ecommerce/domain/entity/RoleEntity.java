package com.ecommerce.ecommerce.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;
import java.util.UUID;

@Entity
@Data
@Table(name = "ROLE_TBL")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID")
    UUID id;

    @Column(name = "NAME", nullable = false, unique = true)
    String name;

    @Column(name = "DESCRIPTION")
    String description;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "ROLES_PERMISSION_TBL",
            joinColumns = @JoinColumn(name = "ROLE_ID"),
            inverseJoinColumns = @JoinColumn(name = "PERMISSION_ID")
    )
    Set<PermissionEntity> permissions;
}
