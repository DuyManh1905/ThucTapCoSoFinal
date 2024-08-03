package com.project.Shop.controller.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.Shop.dto.Cart.CartDto;
import com.project.Shop.exception.NotFoundException;
import com.project.Shop.service.CartService;

@RestController
public class CartRestController {
	
	private final CartService cartService;
	
	public CartRestController(CartService cartService) {
        this.cartService = cartService;
    }
	
    @PostMapping("/api/addToCart")
    public void addToCart(@RequestBody CartDto cartDto) throws NotFoundException {
        cartService.addToCart(cartDto);
    }
    
    
    @ResponseBody
    @PostMapping("/api/deleteCart/{id}")
    public void deleteCart(@PathVariable Long id) {
    	
        cartService.deleteCart(id);
    }

    @ResponseBody
    @PostMapping("/api/updateCart")
    public void updateCart(@RequestBody CartDto cartDto) throws NotFoundException {
        cartService.updateCart(cartDto);
    }
    

    @ResponseBody
    @GetMapping("/api/getAllCart")
    public List<CartDto> getAllCart() {
        return cartService.getAllCartByAccountId();
    }

}
