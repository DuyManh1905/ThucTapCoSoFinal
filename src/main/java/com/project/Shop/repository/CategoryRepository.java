package com.project.Shop.repository;

import com.project.Shop.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    boolean existsByCode(String code);
    List<Category> findAllByDeleteFlagFalse();
}