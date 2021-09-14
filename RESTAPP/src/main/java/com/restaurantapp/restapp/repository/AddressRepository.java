package com.restaurantapp.restapp.repository;

import com.restaurantapp.restapp.model.entity.Address;
import com.restaurantapp.restapp.model.entity.User;
import com.restaurantapp.restapp.repository.baserepo.BaseRepository;

import java.util.List;

public interface AddressRepository extends BaseRepository<Address> {
    List<Address> findAllByUserId(long userId);
}
