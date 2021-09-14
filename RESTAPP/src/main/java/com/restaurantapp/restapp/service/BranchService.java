package com.restaurantapp.restapp.service;

import com.restaurantapp.restapp.exception.BranchNotFoundException;
import com.restaurantapp.restapp.model.dto.AddressDto;
import com.restaurantapp.restapp.model.dto.BranchDto;
import com.restaurantapp.restapp.model.dto.CountyDto;
import com.restaurantapp.restapp.model.dto.MealDto;
import com.restaurantapp.restapp.model.entity.Branch;
import com.restaurantapp.restapp.model.request.create.CreateBranchRequest;
import com.restaurantapp.restapp.model.request.create.CreateMealRequest;
import com.restaurantapp.restapp.model.request.get.BranchPageGetRequest;
import com.restaurantapp.restapp.model.request.update.UpdateBranchRequest;

import java.util.List;

public interface BranchService {

    BranchDto createBranch(CreateBranchRequest request);

    BranchDto getBranchDto(long id);

    List<BranchDto> getBranchesByCounty(BranchPageGetRequest branchPageGetRequest);

    BranchDto changeBranchStatus(long branchId, String value);

    Branch getBranchByid(long id);

    List<BranchDto> getBranchesByStatus(String value) throws Exception;

}
