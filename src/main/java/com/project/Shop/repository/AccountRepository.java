package com.project.Shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.Shop.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{
	//1
    Account findByEmail(String email);

    //2
    @Query(value = "SELECT CONCAT('T', MONTH(a.create_date)) AS month, COUNT(a.id) AS count FROM Account a" +
            " WHERE a.create_date between '2023-01-01' AND '2023-12-31' " +
            "GROUP BY MONTH(create_date)", nativeQuery = true)
    List<Object[]> getMonthlyAccountStatistics(String startDate, String endDate);

    //3
    Account findByCustomer_PhoneNumber(String phoneNumber);

    //4
    Account findTopByOrderByIdDesc();
}
