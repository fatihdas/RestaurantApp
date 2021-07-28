package com.restaurantapp.restapp.repository;

import com.restaurantapp.restapp.model.Address;
import com.restaurantapp.restapp.repository.baserepo.BaseRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends BaseRepository<Address> {
}
