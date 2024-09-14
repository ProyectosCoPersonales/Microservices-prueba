package com.jordancode.productservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jordancode.productservice.Model.Product;
public interface ProductRepository extends JpaRepository<Product,Long>{
    
}
