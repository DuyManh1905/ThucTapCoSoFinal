package com.project.Shop.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.Shop.entity.Product;
import com.project.Shop.entity.ProductDetail;

@Repository
public interface ProductDetailRepository extends JpaRepository<ProductDetail, Long> {
    Page<ProductDetail> getProductDetailsByProductId(Long id, Pageable pageable);

    ProductDetail getProductDetailByProduct(Product product);
    List<ProductDetail> getProductDetailByProductId(Long productId);

    ProductDetail findByBarcodeContainingIgnoreCase(String barcode);

    boolean existsByBarcode(String barcode);

}