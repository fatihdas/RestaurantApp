package com.restaurantapp.restapp.repository;

import com.restaurantapp.restapp.model.Meal;
import com.restaurantapp.restapp.repository.baserepo.BaseRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealRepository extends BaseRepository<Meal> {
}
