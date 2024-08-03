package com.project.Shop.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Nationalized;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "discount_code")
public class DiscountCode {
    @Id	
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Nationalized
    private String code;

    @Nationalized
    private String detail;
    private int type;

    @Column(nullable = true)
    private Integer maximumAmount;

    @Column(nullable = true)
    private Integer percentage;
    
    private Date startDate;
    private Date endDate;
    @Column(nullable = true)
    private Double discountAmount;
    private Double minimumAmountInCart;
    private Integer maximumUsage;
    private int status;
    private boolean deleteFlag;
}
