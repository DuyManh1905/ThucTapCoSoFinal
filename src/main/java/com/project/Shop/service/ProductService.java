package com.project.Shop.service;

import com.project.Shop.dto.Product.ProductDto;
import com.project.Shop.dto.Product.ProductSearchDto;
import com.project.Shop.dto.Product.SearchProductDto;
import com.project.Shop.entity.Product;
import com.project.Shop.exception.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ProductService {

    Page<Product> getAllProduct(Pageable pageable0);

    Page<ProductSearchDto> getAll(Pageable pageable);

    Product save(Product product) throws IOException;

    Product delete(Long id);


    Product getProductByCode(String code);
    
    Product getProductById(String code);
    

    boolean existsByCode(String code);

    Page<Product> search(String productName, Pageable pageable);

    Page<ProductSearchDto> listSearchProduct(String maSanPham,String tenSanPham,Long nhanHang,Long chatLieu,Long theLoai,Integer trangThai,Pageable pageable);

    Page<Product> getAllByStatus(int status, Pageable pageable);

    Optional<Product> getProductById(Long id);
    
    

    Page<ProductDto> searchProduct(SearchProductDto searchDto, Pageable pageable);

    Page<ProductDto> getAllProductApi(Pageable pageable);

    ProductDto getProductByBarcode(String barcode);

    List<ProductDto> getAllProductNoPaginationApi(SearchProductDto searchRequest);

    ProductDto getByProductDetailId(Long detailId);
}
