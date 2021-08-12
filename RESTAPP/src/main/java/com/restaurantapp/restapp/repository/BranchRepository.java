package com.restaurantapp.restapp.repository;

import com.restaurantapp.restapp.model.entity.Branch;
import com.restaurantapp.restapp.model.entity.enumerated.Status;
import com.restaurantapp.restapp.repository.baserepo.BaseRepository;

import java.util.List;

public interface BranchRepository extends BaseRepository<Branch> {
    List<Branch> findBranchesByStatus(Status status);
    List<Branch> findBranchesByAddress_CountyNameContainingIgnoreCase(String address_county_name);
}
