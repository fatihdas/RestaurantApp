package com.restaurantapp.restapp.repository;

import com.restaurantapp.restapp.model.entity.Meal;
import com.restaurantapp.restapp.repository.baserepo.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MealRepository extends BaseRepository<Meal> {
    @Query(value = "SELECT r.user_id FROM meals m " +
            "LEFT JOIN menus mu ON :m.menu_id=mu.id " +
            "LEFT JOIN branches b ON mu.branch_id=b.id " +
            "LEFT JOIN restaurants r ON b.restaurant_id=r.id ", nativeQuery = true)
    long getOwnerIdByMenuId(@Param("m.menu_id") long menuId);
}
