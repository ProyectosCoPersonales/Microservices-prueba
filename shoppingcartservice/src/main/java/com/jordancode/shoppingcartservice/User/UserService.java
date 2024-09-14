package com.jordancode.shoppingcartservice.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.jordancode.shoppingcartservice.Response.UserProfileResponse;

@Service
public class UserService {

    @Autowired
    private RestTemplate restTemplate;

    public UserProfileResponse getUserById(Long userId, String jwt) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + jwt);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        return restTemplate.exchange(
            "http://localhost:8090/auth/" + userId, 
            HttpMethod.GET, 
            entity, 
            UserProfileResponse.class
        ).getBody();
    }
}
