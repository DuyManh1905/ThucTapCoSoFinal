package com.project.Shop.service.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Shop.dto.Product.ProductDetailDto;
import com.project.Shop.entity.Product;
import com.project.Shop.entity.ProductDetail;
import com.project.Shop.entity.ProductDiscount;
import com.project.Shop.exception.NotFoundException;
import com.project.Shop.repository.ProductDetailRepository;
import com.project.Shop.repository.ProductDiscountRepository;
import com.project.Shop.repository.ProductRepository;
import com.project.Shop.service.ProductDetailService;

@Service
public class ProductDetailServiceImpl implements ProductDetailService {
@Autowired
private ProductDetailRepository productDetailRepository;
    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductDiscountRepository productDiscountRepository;

    @Override
    public ProductDetail save(ProductDetail productDetail) {
        if (productDetail.getId() == null){
            return productDetailRepository.save(productDetail);
        }else{
            ProductDetail productDetail1 = productDetailRepository.getOne(productDetail.getId());
            int i = productDetail.getQuantity();
            int b = productDetail1.getQuantity();
            productDetail.setQuantity(b - i);
            return productDetailRepository.save(productDetail);
        }

    }


    @Override
    public ProductDetail getProductDetailByProductCode(String code){
        Product product = productRepository.findByCode(code);

        return productDetailRepository.getProductDetailByProduct(product);

    }

    @Override
    public List<ProductDetailDto> getByProductId(Long id) throws NotFoundException {
        Product product = productRepository.findById(id).orElseThrow( () -> new NotFoundException("Product not found"));
        List<ProductDetail> productDetails = productDetailRepository.getProductDetailByProductId(id);
        List<ProductDetailDto> productDetailDTOs = new ArrayList<>();

        for (ProductDetail productDetail : productDetails) {
            ProductDetailDto productDetailDTO = new ProductDetailDto();
            // Set properties of productDetailDTO based on productDetail
            productDetailDTO.setId(productDetail.getId());
            productDetailDTO.setProductId(productDetail.getProduct().getId());
            productDetailDTO.setPrice(productDetail.getPrice());
            productDetailDTO.setSize(productDetail.getSize());
            productDetailDTO.setColor(productDetail.getColor());
            productDetailDTO.setQuantity(productDetail.getQuantity());

            ProductDiscount productDiscount = productDiscountRepository.findValidDiscountByProductDetailId(productDetail.getId());
            if(productDiscount != null) {
//                Date endDate = productDiscount.getEndDate();
//                Date currentDate = new Date();
//                if (currentDate.compareTo(endDate) > 0) {
//                }
                productDetailDTO.setDiscountedPrice(productDiscount.getDiscountedAmount());

            }
            // Set other properties as needed
            productDetailDTOs.add(productDetailDTO);
        }
        return productDetailDTOs;
    }

}
