package com.ecommerce.ecommerce.repository;

import com.ecommerce.ecommerce.domain.entity.InvalidtedToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<InvalidtedToken, String> {
}
