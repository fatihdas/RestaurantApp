package com.restaurantapp.restapp.model.converter.create.request;

import com.restaurantapp.restapp.model.entity.Address;
import com.restaurantapp.restapp.model.entity.Branch;
import com.restaurantapp.restapp.model.entity.Restaurant;
import com.restaurantapp.restapp.model.entity.enumerated.BranchStatus;
import com.restaurantapp.restapp.model.request.create.CreateBranchRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CreateBranchRequestConverter {

    private final CreateAddressRequestConverter createAddressRequestConverter;

    public CreateBranchRequestConverter(CreateAddressRequestConverter createAddressRequestConverter) {
        this.createAddressRequestConverter = createAddressRequestConverter;
    }

    public Branch convert(CreateBranchRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("invalid request!");
        }
        Branch branch = new Branch();
        branch.setId(request.getId());
        branch.setCommentList(new ArrayList<>());
        branch.setBranchStatus(BranchStatus.WAITING);
        branch.setName(request.getName());
        request.getCreateAddressRequest().setBranchId(branch.getId());
        Address address = createAddressRequestConverter.convert(request.getCreateAddressRequest());
        address.setBranch(branch);
        branch.setAddress(address);
        branch.setRestaurant(Restaurant.builder().id(request.getRestaurantId()).build());

        return branch;

    }
}
