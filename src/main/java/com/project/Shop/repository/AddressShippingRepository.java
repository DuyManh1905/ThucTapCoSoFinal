package com.project.Shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.Shop.entity.AddressShipping;

public interface AddressShippingRepository extends JpaRepository<AddressShipping, Long> {
    List<AddressShipping> findAllByCustomer_Account_Id(Long accountId);
}
