package com.restaurantapp.restapp.repository;

import com.restaurantapp.restapp.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant,Long> {
}
