package com.project.Shop.repository.Specification;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.project.Shop.dto.Bill.SearchBillDto;
import com.project.Shop.entity.Bill;
import com.project.Shop.entity.Customer;
import com.project.Shop.entity.enumClass.BillStatus;

public class BillSpecification implements Specification<Bill> {
    private SearchBillDto searchBillDto;

    public BillSpecification(SearchBillDto searchBillDto) {
        this.searchBillDto = searchBillDto;
    }

    @Override
    public Predicate toPredicate(Root<Bill> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        if (searchBillDto.getKeyword() != null) {
            String keyword = searchBillDto.getKeyword();

            // Create an alias for the customer join to make the left join explicit
            Join<Bill, Customer> customerJoin = root.join("customer", JoinType.LEFT);

            Predicate billCodePredicate = criteriaBuilder.like(root.get("code"), "%" + keyword + "%");
            Predicate customerNamePredicate = criteriaBuilder.like(customerJoin.get("name"), "%" + keyword + "%");
            Predicate phoneNumberPredicate = criteriaBuilder.like(customerJoin.get("phoneNumber"), "%" + keyword + "%");

            Predicate combinedPredicate = criteriaBuilder.or(
                    billCodePredicate,
//                    criteriaBuilder.isNull(root.get("customer")),
                    customerNamePredicate,
                    phoneNumberPredicate
            );

            predicates.add(combinedPredicate);
        }
        LocalDateTime sevenDaysAgoStartOfDay = LocalDateTime.now().minusDays(7).with(LocalTime.MIN);
        LocalDateTime nowEndOfDay = LocalDateTime.now().with(LocalTime.MAX);

        predicates.add(criteriaBuilder.equal(root.get("status"), BillStatus.HOAN_THANH));
        predicates.add(criteriaBuilder.between(root.get("createDate"), sevenDaysAgoStartOfDay, nowEndOfDay));
        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
    }
}
