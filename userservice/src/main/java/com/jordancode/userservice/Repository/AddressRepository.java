package com.jordancode.userservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jordancode.userservice.Model.Address;
import com.jordancode.userservice.Model.User;

public interface AddressRepository extends JpaRepository<Address,Long>{
    Address findByOwner(User owner);
}
