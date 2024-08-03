package com.project.Shop.service;

import com.project.Shop.dto.Category.CategoryDto;
import com.project.Shop.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    Page<Category> getAllCategory(Pageable pageable);

    Category createCategory(Category category);

    Category updateCategory(Category category);

    void delete(Long id);

    boolean existsById(Long id);

    Optional<Category> findById(Long id);

    List<Category> getAll();

    CategoryDto createCategoryApi(CategoryDto categoryDto);
}
