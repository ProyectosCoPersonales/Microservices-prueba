package com.jordancode.shoppingcartservice.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.jordancode.shoppingcartservice.Response.ProductResponse;

@Service
public class ProductService {

    @Autowired
    private RestTemplate restTemplate;

    public ProductResponse getProductById(Long productId, String jwt) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + jwt);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        return restTemplate.exchange(
            "http://localhost:8080/api/products/" + productId, 
            HttpMethod.GET, 
            entity, 
            ProductResponse.class
        ).getBody();
    }
}


