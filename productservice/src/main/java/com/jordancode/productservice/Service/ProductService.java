package com.jordancode.productservice.Service;

import com.jordancode.productservice.Model.Product;
import java.util.List;

public interface ProductService {
    Product createProduct(Product product);
    Product getProductById(Long productId);
    Product updateProductById(Long productId, Product product);
    List<Product> getAllProducts();
}
