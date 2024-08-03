package com.project.Shop.service;

import com.project.Shop.dto.ProductDiscount.ProductDiscountCreateDto;
import com.project.Shop.dto.ProductDiscount.ProductDiscountDto;
import com.project.Shop.entity.ProductDiscount;

import java.util.List;

public interface ProductDiscountService {
    List<ProductDiscount> getAllProductDiscount();

    ProductDiscountDto updateCloseProductDiscount(Long discountId, boolean closed);

    List<ProductDiscountDto> createProductDiscountMultiple(ProductDiscountCreateDto productDiscountCreateDto);
}
