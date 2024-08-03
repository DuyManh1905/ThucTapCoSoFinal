package com.project.Shop.dto.Order;

import com.project.Shop.dto.Product.ProductDetailDto;
import lombok.Data;

@Data
public class OrderDetailDto {
    private Integer quantity;
    private Long productDetailId;
}
