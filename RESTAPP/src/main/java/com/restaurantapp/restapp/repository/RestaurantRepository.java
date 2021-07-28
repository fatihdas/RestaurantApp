package com.restaurantapp.restapp.repository;

import com.restaurantapp.restapp.model.Restaurant;
import com.restaurantapp.restapp.repository.baserepo.BaseRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends BaseRepository<Restaurant> {
}
