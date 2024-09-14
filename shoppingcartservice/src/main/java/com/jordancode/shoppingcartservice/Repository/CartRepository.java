package com.jordancode.shoppingcartservice.Repository;


import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jordancode.shoppingcartservice.Model.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long>{
    Cart findByUserId(Long userId);
    List<Cart> findAll();
}
