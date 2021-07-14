package com.restaurantapp.restapp.repository;

import com.restaurantapp.restapp.model.County;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountyRepository extends JpaRepository<County,Long> {
}
