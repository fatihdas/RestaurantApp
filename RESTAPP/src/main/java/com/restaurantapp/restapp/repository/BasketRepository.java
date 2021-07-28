package com.restaurantapp.restapp.repository;

import com.restaurantapp.restapp.model.Basket;
import com.restaurantapp.restapp.repository.baserepo.BaseRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasketRepository extends BaseRepository<Basket> {
}
