package com.restaurantapp.restapp.model.converter.create.request;

import com.restaurantapp.restapp.model.entity.Branch;
import com.restaurantapp.restapp.service.impl.BranchServiceImpl;
import org.springframework.stereotype.Component;

@Component
public class BranchIdToEntityConverter {

    private final BranchServiceImpl branchService;

    public BranchIdToEntityConverter(BranchServiceImpl branchService) {
        this.branchService = branchService;
    }

    public Branch convert(long id){

        return branchService.getBranchByid(id);
    }
}
