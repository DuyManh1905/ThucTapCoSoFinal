package com.project.Shop.dto.Product;

import com.project.Shop.entity.Image;
import com.project.Shop.entity.Material;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Long id;
    private String code;
    private String name;
    private String description;
    private String categoryName;
    private String imageUrl;
    private Double priceMin;

    private List<ProductDetailDto> productDetailDtos;
    private LocalDateTime createDate;
    private LocalDateTime updatedDate;
    private boolean isDiscounted;
}
