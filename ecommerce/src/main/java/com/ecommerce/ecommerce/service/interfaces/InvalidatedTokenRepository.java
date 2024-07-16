package com.ecommerce.ecommerce.service.interfaces;

import com.ecommerce.ecommerce.domain.entity.InvalidtedToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvalidatedTokenRepository extends JpaRepository<InvalidtedToken, String> {
}
