package com.project.Shop.repository;

import com.project.Shop.entity.VerificationCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationRepository extends JpaRepository<VerificationCode, Long> {
    VerificationCode findByCode(String code);
}
