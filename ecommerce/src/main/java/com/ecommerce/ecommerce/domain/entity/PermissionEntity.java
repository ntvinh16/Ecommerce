package com.ecommerce.ecommerce.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;
import java.util.UUID;

@Entity
@Data
@Table(name = "PERMISSION_TBL")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PermissionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID")
    UUID id;

    @Column(name = "NAME", nullable = false, unique = true)
    String name;

    @Column(name = "DESCRIPTION")
    String description;
}
