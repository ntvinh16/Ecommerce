package com.ecommerce.ecommerce.repository;

import com.ecommerce.ecommerce.domain.entity.PermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<PermissionEntity, String> {
    PermissionEntity findByName(String name);
}