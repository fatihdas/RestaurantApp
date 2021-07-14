package com.restaurantapp.restapp.repository;

import com.restaurantapp.restapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByName(String name);
}
