package com.jordancode.userservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jordancode.userservice.Model.User;

public interface UserRepository extends JpaRepository<User,Long>{
    User findByEmail(String email);
}
