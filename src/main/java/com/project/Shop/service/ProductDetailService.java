package com.project.Shop.service;

import com.project.Shop.dto.Product.ProductDetailDto;
import com.project.Shop.entity.Product;
import com.project.Shop.entity.ProductDetail;
import javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductDetailService {
    ProductDetail save(ProductDetail productDetail);

    ProductDetail getProductDetailByProductCode(String code) throws NotFoundException;

    List<ProductDetailDto> getByProductId(Long id) throws com.project.Shop.exception.NotFoundException;
}
