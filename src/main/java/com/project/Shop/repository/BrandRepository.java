package com.project.Shop.repository;

import com.project.Shop.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BrandRepository extends JpaRepository<Brand, Long> {
    boolean existsByCode(String code);
    List<Brand> findAllByDeleteFlagFalse();
}