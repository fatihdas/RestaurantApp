package com.restaurantapp.restapp.repository;

import com.restaurantapp.restapp.model.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealRepository extends JpaRepository<Meal,Long> {
}
