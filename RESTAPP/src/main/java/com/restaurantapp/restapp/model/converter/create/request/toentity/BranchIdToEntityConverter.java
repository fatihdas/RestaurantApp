package com.restaurantapp.restapp.model.converter.create.request.toentity;

import com.restaurantapp.restapp.model.converter.dto.toentity.BranchDtoToEntityConverter;
import com.restaurantapp.restapp.model.entity.Branch;
import com.restaurantapp.restapp.service.impl.BranchServiceImpl;
import org.springframework.stereotype.Component;

@Component
public class BranchIdToEntityConverter {

    private final BranchServiceImpl branchService;
    private final BranchDtoToEntityConverter branchDtoToEntityConverter;

    public BranchIdToEntityConverter(BranchServiceImpl branchService, BranchDtoToEntityConverter branchDtoToEntityConverter) {
        this.branchService = branchService;
        this.branchDtoToEntityConverter = branchDtoToEntityConverter;
    }

    public Branch convert(long id){

        return branchDtoToEntityConverter.convert(branchService.getBranch(id));
    }
}
