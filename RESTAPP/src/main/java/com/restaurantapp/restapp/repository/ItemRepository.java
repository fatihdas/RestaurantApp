package com.restaurantapp.restapp.repository;

import com.restaurantapp.restapp.model.Item;
import com.restaurantapp.restapp.repository.baserepo.BaseRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends BaseRepository<Item> {
}
