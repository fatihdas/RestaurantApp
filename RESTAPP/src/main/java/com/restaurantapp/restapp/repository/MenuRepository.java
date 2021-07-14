package com.restaurantapp.restapp.repository;

import com.restaurantapp.restapp.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu,Long> {
}
