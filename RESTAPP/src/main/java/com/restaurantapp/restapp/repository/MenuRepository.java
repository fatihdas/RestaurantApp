package com.restaurantapp.restapp.repository;

import com.restaurantapp.restapp.model.Menu;
import com.restaurantapp.restapp.repository.baserepo.BaseRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends BaseRepository<Menu> {
}
