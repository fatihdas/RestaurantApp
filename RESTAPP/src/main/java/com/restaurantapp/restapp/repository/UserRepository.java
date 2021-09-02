package com.restaurantapp.restapp.repository;

import com.restaurantapp.restapp.model.entity.User;
import com.restaurantapp.restapp.repository.baserepo.BaseRepository;


public interface UserRepository extends BaseRepository<User> {
    User findByName(String name);

}
