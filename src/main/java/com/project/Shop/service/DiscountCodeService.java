package com.project.Shop.service;


import com.project.Shop.dto.DiscountCode.DiscountCodeDto;
import com.project.Shop.dto.DiscountCode.SearchDiscountCodeDto;
import com.project.Shop.dto.Product.SearchProductDto;
import com.project.Shop.entity.DiscountCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DiscountCodeService {
    Page<DiscountCodeDto> getAllDiscountCode(SearchDiscountCodeDto searchDiscountCodeDto, Pageable pageable);
    DiscountCodeDto saveDiscountCode(DiscountCodeDto discountCodeDto);
    DiscountCodeDto updateDiscountCode(DiscountCodeDto discountCodeDto);

    DiscountCodeDto getDiscountCodeById(Long id);
    DiscountCodeDto getDiscountCodeByCode(Long code);
    DiscountCodeDto updateStatus(Long discountCodeId, int status);
    Page<DiscountCodeDto> getAllAvailableDiscountCode(Pageable pageable);
}
