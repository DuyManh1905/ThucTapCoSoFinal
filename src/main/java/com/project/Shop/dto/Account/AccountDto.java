package com.project.Shop.dto.Account;

import com.project.Shop.dto.AddressShipping.AddressShippingDto;
import lombok.Data;

import java.util.List;

@Data
public class AccountDto {
    private String phoneNumber;
    private String name;
    private String email;
    private String password;
    private List<AddressShippingDto> addressShippingList;
}
