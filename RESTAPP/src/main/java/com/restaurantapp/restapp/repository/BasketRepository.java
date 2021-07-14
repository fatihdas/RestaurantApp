package com.restaurantapp.restapp.repository;

import com.restaurantapp.restapp.model.Basket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasketRepository extends JpaRepository<Basket,Long> {
}
