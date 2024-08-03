package com.project.Shop.dto.Product;

import com.project.Shop.entity.ProductDetail;
import lombok.Data;

import java.util.List;

@Data
public class CreateProductDetailsForm {
    private List<ProductDetail> productDetailList;
}
