package com.project.Shop.repository;

import com.project.Shop.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    boolean existsByOrderId(String orderId);
    Payment findByOrderId(String orderId);

}
