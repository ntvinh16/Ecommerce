package com.ecommerce.ecommerce.repository;

import com.ecommerce.ecommerce.domain.entity.PermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PermissionRepository extends JpaRepository<PermissionEntity, UUID> {
    PermissionEntity findByName(String name);
}
