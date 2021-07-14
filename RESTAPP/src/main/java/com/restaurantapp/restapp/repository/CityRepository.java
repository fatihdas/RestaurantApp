package com.restaurantapp.restapp.repository;

import com.restaurantapp.restapp.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City,Long> {
}
