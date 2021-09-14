package com.restaurantapp.restapp.model.converter.entity.todto;

import com.restaurantapp.restapp.model.dto.BranchDto;
import com.restaurantapp.restapp.model.entity.Branch;
import org.springframework.stereotype.Component;

@Component
public class BranchEntityToDtoConverter {

    public BranchDto convert(Branch branch) {
        BranchDto branchDto = new BranchDto();
        branchDto.setId(branch.getId());
        branchDto.setName(branch.getName());
        branchDto.setAddressId(branch.getAddress().getId());
        branchDto.setBranchStatus(branch.getBranchStatus());
        branchDto.setRestaurantId(branch.getRestaurant().getId());

        if (branch.getMenu() != null) {
            branchDto.setMenuId(branch.getMenu().getId());
        }

        return branchDto;
    }
}
