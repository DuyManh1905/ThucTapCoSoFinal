package com.project.Shop.service;

import com.project.Shop.dto.Brand.BrandDto;
import com.project.Shop.entity.Brand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BrandService {

    Page<Brand> getAllBrand(Pageable pageable);

    Brand createBrand(Brand brand);
    Brand updateBrand(Long id, Brand brand);
    Brand save(Brand brand);

    void delete(Long id);

    Optional<Brand> findById(Long id);

    List<Brand> getAll();

    BrandDto createBrandApi(BrandDto brandDto);

    boolean existsById(Long id);
}
