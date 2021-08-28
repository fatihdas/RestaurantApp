package com.restaurantapp.restapp.service;

import com.restaurantapp.restapp.model.dto.AddressDto;
import com.restaurantapp.restapp.model.dto.BranchDto;
import com.restaurantapp.restapp.model.dto.CountyDto;
import com.restaurantapp.restapp.model.request.create.CreateBranchRequest;
import com.restaurantapp.restapp.model.request.update.UpdateBranchRequest;

import java.util.List;

public interface BranchService {

    BranchDto createBranch(CreateBranchRequest request);

    List<BranchDto> getAllBranches();

    BranchDto getBranchDto(long id);

    List<BranchDto> getNearBranches(String countyName);

    String updateBranch(UpdateBranchRequest request, long id);

    void deleteBranch(long id);

    List<BranchDto> getWaitingBranches(String value) throws Exception;
}
