package com.restaurantapp.restapp.repository;

import com.restaurantapp.restapp.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Long> {
}
