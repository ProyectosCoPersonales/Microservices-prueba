package com.jordancode.shoppingcartservice.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartRequest {
    private Long productId;
    private Integer quantity;
    private Long userId;
}