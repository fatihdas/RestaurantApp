package com.restaurantapp.restapp.repository;

import com.restaurantapp.restapp.model.User;
import com.restaurantapp.restapp.repository.baserepo.BaseRepository;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends BaseRepository<User> {
    User findByName(String name);
}
