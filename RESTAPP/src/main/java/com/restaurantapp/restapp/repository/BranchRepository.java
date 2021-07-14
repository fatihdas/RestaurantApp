package com.restaurantapp.restapp.repository;

import com.restaurantapp.restapp.model.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BranchRepository extends JpaRepository<Branch,Long> {
}
