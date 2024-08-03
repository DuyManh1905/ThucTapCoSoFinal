package com.project.Shop.controller.api;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.Shop.dto.AddressShipping.AddressShippingDto;
import com.project.Shop.service.AddressShippingService;

@Controller
public class AddressShippingController {

    private final AddressShippingService addressShippingService;

    public AddressShippingController(AddressShippingService addressShippingService) {
        this.addressShippingService = addressShippingService;
    }

    @ResponseBody
    @PostMapping("api/public/addressShipping")
    public ResponseEntity<AddressShippingDto> createAddressShipping(@RequestBody AddressShippingDto addressShippingDto){
        return ResponseEntity.ok(addressShippingService.saveAddressShippingUser(addressShippingDto));
    }

    @ResponseBody
    @DeleteMapping("/api/deleteAddress/{id}")
    public void deleteAddressShipping(@PathVariable Long id) {
        addressShippingService.deleteAddressShipping(id);
    }
}
