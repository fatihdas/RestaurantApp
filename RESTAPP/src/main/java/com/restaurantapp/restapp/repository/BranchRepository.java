package com.restaurantapp.restapp.repository;

import com.restaurantapp.restapp.enumerated.Status;
import com.restaurantapp.restapp.model.Branch;
import com.restaurantapp.restapp.repository.baserepo.BaseRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BranchRepository extends BaseRepository<Branch> {
    List<Branch> findAllByStatus(Status status);
}
