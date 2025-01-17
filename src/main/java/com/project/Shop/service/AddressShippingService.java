package com.project.Shop.service;

import com.project.Shop.dto.AddressShipping.AddressShippingDto;
import com.project.Shop.dto.AddressShipping.AddressShippingDtoAdmin;
import com.project.Shop.entity.AddressShipping;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AddressShippingService {
    List<AddressShippingDto> getAddressShippingByAccountId();
    AddressShippingDto saveAddressShippingUser(AddressShippingDto addressShippingDto);

    AddressShippingDto saveAddressShippingAdmin(AddressShippingDtoAdmin addressShippingDto);

    void deleteAddressShipping(Long id);
}
