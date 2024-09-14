package com.jordancode.shoppingcartservice.Service;

import java.util.List;

import com.jordancode.shoppingcartservice.Model.Cart;

public interface CartService {
    Cart addProductToCart(Long userId, Long productId, Integer quantity, String jwt);
    Cart removeProductFromCart(Long userId, Long productId, String jwt);
    Cart clearCart(Long userId, String jwt);
    List<Cart> findAll();
}
