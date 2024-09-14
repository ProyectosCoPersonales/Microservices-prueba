package com.jordancode.shoppingcartservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jordancode.shoppingcartservice.Product.ProductService;
import com.jordancode.shoppingcartservice.Response.ProductResponse;
import com.jordancode.shoppingcartservice.Response.UserProfileResponse;
import com.jordancode.shoppingcartservice.User.UserService;

@RestController
@RequestMapping("/probando")
public class ShoppingController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getDatosProduct(
            @PathVariable Long id,
            @RequestHeader("Authorization") String jwt) {
        return new ResponseEntity<>(productService.getProductById(id, jwt), HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserProfileResponse> getDatosUser(
            @PathVariable Long id,
            @RequestHeader("Authorization") String jwt) {
        return new ResponseEntity<>(userService.getUserById(id, jwt), HttpStatus.OK);
    }
}
