package com.restaurantapp.restapp.repository;

import com.restaurantapp.restapp.model.entity.Branch;
import com.restaurantapp.restapp.model.entity.enumerated.BranchStatus;
import com.restaurantapp.restapp.repository.baserepo.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BranchRepository extends BaseRepository<Branch> {
    List<Branch> findBranchesByBranchStatus(BranchStatus branchStatus);

    Page<Branch> findBranchesByAddress_CountyId(long countyId, Pageable pageable);

    @Query(value = "SELECT r.user_id FROM branches b " +
            "LEFT JOIN restaurants r ON b.restaurant_id = :r.id",nativeQuery = true)
    long getOwnerIdByRestaurantId(@Param("r.id") long restaurantId);

}
