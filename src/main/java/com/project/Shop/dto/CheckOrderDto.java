package com.project.Shop.dto;

import com.project.Shop.dto.Product.ProductDetailDto;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CheckOrderDto {
    private Long productDetailId;
    private String quantity;
}
