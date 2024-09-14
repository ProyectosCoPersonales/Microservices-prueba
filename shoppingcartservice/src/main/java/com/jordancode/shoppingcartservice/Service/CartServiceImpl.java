package com.jordancode.shoppingcartservice.Service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jordancode.shoppingcartservice.Model.Cart;
import com.jordancode.shoppingcartservice.Model.CartItem;
import com.jordancode.shoppingcartservice.Product.ProductService;
import com.jordancode.shoppingcartservice.Repository.CartRepository;
import com.jordancode.shoppingcartservice.Response.ProductResponse;
import com.jordancode.shoppingcartservice.Response.UserProfileResponse;
import com.jordancode.shoppingcartservice.User.UserService;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Override
    public Cart addProductToCart(Long userId, Long productId, Integer quantity, String jwt) {
        try {
            UserProfileResponse user = userService.getUserById(userId, jwt);
            ProductResponse product = productService.getProductById(productId, jwt);

            if (user == null || product == null) {
                System.out.println("User or product not found");
                return null;
            }

            Cart cart = cartRepository.findByUserId(userId);
            if (cart == null) {
                cart = new Cart();
                cart.setUserId(userId);
            }

            Optional<CartItem> existingItem = cart.getItems().stream()
                    .filter(item -> item.getProductId().equals(product.getId()))
                    .findFirst();

            if (existingItem.isPresent()) {
                CartItem cartItem = existingItem.get();
                cartItem.setQuantity(cartItem.getQuantity() + quantity);
                cartItem.updateTotal();
            } else {
                CartItem newItem = new CartItem();
                newItem.setProductId(product.getId());
                newItem.setUnitPrice(product.getPrice());
                newItem.setQuantity(quantity);
                newItem.updateTotal();
                cart.getItems().add(newItem);
            }

            cart.updateCartSummary();
            return cartRepository.save(cart);
        } catch (Exception e) {
            System.out.println("Error adding product to cart: " + e.getMessage());
            return null;
        }
    }

    @Override
    public Cart removeProductFromCart(Long userId, Long productId, String jwt) {
        try {
            UserProfileResponse user = userService.getUserById(userId, jwt);
            if (user == null) {
                System.out.println("User not found");
                return null;
            }

            Cart cart = cartRepository.findByUserId(userId);
            if (cart == null) {
                System.out.println("Cart not found");
                return null;
            }

            boolean removed = cart.getItems().removeIf(item -> item.getProductId().equals(productId));
            if (!removed) {
                System.out.println("Product not found in cart");
            }
            cart.updateCartSummary();
            return cartRepository.save(cart);
        } catch (Exception e) {
            System.out.println("Error removing product from cart: " + e.getMessage());
            return null;
        }
    }

    @Override
    public Cart clearCart(Long userId, String jwt) {
        try {
            UserProfileResponse user = userService.getUserById(userId, jwt);
            if (user == null) {
                System.out.println("User not found");
                return null;
            }

            Cart cart = cartRepository.findByUserId(userId);
            if (cart == null) {
                System.out.println("Cart not found");
                return null;
            }

            cart.getItems().clear();
            cart.updateCartSummary();
            return cartRepository.save(cart);
        } catch (Exception e) {
            System.out.println("Error clearing cart: " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<Cart> findAll() {
        return cartRepository.findAll();
    }
}