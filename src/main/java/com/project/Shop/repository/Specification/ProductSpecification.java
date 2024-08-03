package com.project.Shop.repository.Specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.springframework.data.jpa.domain.Specification;

import com.project.Shop.dto.Product.SearchProductDto;
import com.project.Shop.entity.Product;
import com.project.Shop.entity.ProductDetail;


// tạo ra các tiêu chí truy vấn cho thực thể Product dựa trên các tiêu chí tìm kiếm được truyền vào từ SearchProductDto.
public class ProductSpecification implements Specification<Product> {
    private SearchProductDto searchProductDto;

    public ProductSpecification(SearchProductDto searchRequest) {
        this.searchProductDto = searchRequest;
    }

    @Override
    public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
    	
        List<Predicate> predicates = new ArrayList<>();
        
        if (searchProductDto.getMinPrice() != null) {
        	// Tạo một subquery để lấy giá của ProductDetail đầu tiên
            Subquery<Double> subquery = query.subquery(Double.class);
            Root<Product> productRoot = subquery.from(Product.class);
            Join<Product, ProductDetail> productDetailJoin = productRoot.join("productDetails");
            subquery.select(criteriaBuilder.min(productDetailJoin.get("price")));
            subquery.where(criteriaBuilder.equal(productRoot.get("id"), root.get("id")));
            Predicate predicate = criteriaBuilder.greaterThanOrEqualTo(subquery, searchProductDto.getMinPrice());

            predicates.add(predicate);        }

        if (searchProductDto.getMaxPrice() != null) {
            // Tạo một subquery để lấy giá của ProductDetail đầu tiên
            Subquery<Double> subquery = query.subquery(Double.class);
            Root<Product> productRoot = subquery.from(Product.class);
            Join<Product, ProductDetail> productDetailJoin = productRoot.join("productDetails");
            subquery.select(criteriaBuilder.min(productDetailJoin.get("price")));
            subquery.where(criteriaBuilder.equal(productRoot.get("id"), root.get("id")));
            Predicate predicate = criteriaBuilder.lessThanOrEqualTo(subquery, searchProductDto.getMaxPrice());

            predicates.add(predicate);

        }

        if (searchProductDto.getProductName() != null) {
            predicates.add(criteriaBuilder.like(root.get("name"), "%"+searchProductDto.getProductName()+"%"));
        }

        if(searchProductDto.getCode() != null) {
            predicates.add(criteriaBuilder.like(root.get("code"), "%"+searchProductDto.getCode()+"%"));

        }

        if(searchProductDto.getKeyword() != null) {
            String keyword = searchProductDto.getKeyword();

            Predicate productNamePredicate = criteriaBuilder.like(root.get("name"), "%" + keyword + "%");
            Predicate productCodePredicate = criteriaBuilder.like(root.get("code"), "%" + keyword + "%");
            Predicate combinedPredicate = criteriaBuilder.or(productNamePredicate, productCodePredicate);

            predicates.add(combinedPredicate);
        }

        if(searchProductDto.getCategoryId() != null) {
            Predicate predicate = root.get("category").get("id").in(searchProductDto.getCategoryId());
            predicates.add(predicate);
        }

        if(searchProductDto.getGender() != null) {
            Predicate predicate = criteriaBuilder.equal(root.get("gender"), searchProductDto.getGender());
            predicates.add(predicate);
        }

        predicates.add(criteriaBuilder.equal(root.get("status"), 1));
        predicates.add(criteriaBuilder.equal(root.get("deleteFlag"), false));
        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
    }
}
