package com.project.Shop.controller.user;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.Shop.dto.AddressShipping.AddressShippingDto;
import com.project.Shop.dto.Cart.CartDto;
import com.project.Shop.dto.DiscountCode.DiscountCodeDto;
import com.project.Shop.service.AddressShippingService;
import com.project.Shop.service.BillService;
import com.project.Shop.service.CartService;
import com.project.Shop.service.DiscountCodeService;

@Controller
public class ShoppingCartController {
    private final CartService cartService;

    private final DiscountCodeService discountCodeService;

    private final AddressShippingService addressShippingService;

    public ShoppingCartController(CartService cartService, BillService billService, DiscountCodeService discountCodeService, AddressShippingService addressShippingService) {
        this.cartService = cartService;
        this.discountCodeService = discountCodeService;
        this.addressShippingService = addressShippingService;
    }

    @GetMapping("/shoping-cart")
    public String viewShoppingCart(Model model) {
        List<CartDto> cartDtoList = cartService.getAllCartByAccountId();
        
        Page<DiscountCodeDto> discountCodeList = discountCodeService.getAllAvailableDiscountCode(PageRequest.of(0, 15));
        
        List<AddressShippingDto> addressShippingDtos = addressShippingService.getAddressShippingByAccountId();
        
        model.addAttribute("discountCodes", discountCodeList.getContent());
        model.addAttribute("addressShippings", addressShippingDtos);
        model.addAttribute("carts", cartDtoList);
        return "user/shoping-cart";
    }

}

