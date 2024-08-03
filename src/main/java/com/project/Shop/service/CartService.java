package com.project.Shop.service;

import com.project.Shop.dto.Cart.CartDto;
import com.project.Shop.dto.Order.OrderDto;
import com.project.Shop.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CartService {
//    Page<Cart> carts(Pageable pageable);
    List<CartDto> getAllCart();
    List<CartDto> getAllCartByAccountId();
    void addToCart(CartDto cartDto) throws NotFoundException;

    void updateCart(CartDto cartDto) throws NotFoundException;

    void orderUser(OrderDto orderDto);
    OrderDto orderAdmin(OrderDto orderDto);

    void deleteCart(Long id);
}
