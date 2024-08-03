package com.project.Shop.dto.DiscountCode;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class SearchDiscountCodeDto {
    private String keyword;
    private String detail;
    private String code;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;
    private Integer discountCodeType;
    private Integer maximumUsage;
    private Integer status;

}
