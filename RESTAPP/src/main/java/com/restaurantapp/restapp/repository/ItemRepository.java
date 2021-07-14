package com.restaurantapp.restapp.repository;

import com.restaurantapp.restapp.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item,Long> {
}
