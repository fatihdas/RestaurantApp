package com.restaurantapp.restapp.repository;

import com.restaurantapp.restapp.model.entity.Branch;
import com.restaurantapp.restapp.model.entity.enumerated.BranchStatus;
import com.restaurantapp.restapp.repository.baserepo.BaseRepository;

import java.util.List;

public interface BranchRepository extends BaseRepository<Branch> {
    List<Branch> findBranchesByBranchStatus(BranchStatus branchStatus);
    List<Branch> findBranchesByAddress_CountyId(long countyId);
}
