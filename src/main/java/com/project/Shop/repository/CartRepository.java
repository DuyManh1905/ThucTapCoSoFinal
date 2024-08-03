package com.project.Shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.Shop.entity.Cart;
import com.project.Shop.entity.ProductDetail;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    List<Cart> findAllByAccount_Id(Long accountId);
    boolean existsByProductDetail_IdAndAccount_Id(Long productDetailId, Long accountId);
    Cart findByProductDetail_IdAndAccount_Id(Long productDetailId, Long accountId);
    Cart findByProductDetail(ProductDetail productDetail);
    void deleteAllByAccount_Id(Long accountId);

}
