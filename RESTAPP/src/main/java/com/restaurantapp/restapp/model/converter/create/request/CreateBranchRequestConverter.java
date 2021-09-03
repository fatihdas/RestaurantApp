package com.restaurantapp.restapp.model.converter.create.request;

import com.restaurantapp.restapp.model.entity.Branch;
import com.restaurantapp.restapp.model.entity.Restaurant;
import com.restaurantapp.restapp.model.request.create.CreateBranchRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CreateBranchRequestConverter {

    private final CreateMenuRequestConverter createMenuRequestConverter;
    private final CreateAddressRequestConverter createAddressRequestConverter;

    public CreateBranchRequestConverter(CreateMenuRequestConverter createMenuRequestConverter,
                                        CreateAddressRequestConverter createAddressRequestConverter) {
        this.createMenuRequestConverter = createMenuRequestConverter;
        this.createAddressRequestConverter = createAddressRequestConverter;
    }

    public Branch convert(CreateBranchRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("invalid request!");
        }
        Branch branch = new Branch();
        branch.setId(request.getId());
        branch.setMenu(createMenuRequestConverter.convert(request.getCreateMenuRequest()));
        branch.setCommentList(new ArrayList<>());
        branch.setBranchStatus(request.getBranchStatus());
        branch.setName(request.getName());
        branch.setAddress(createAddressRequestConverter.convert(request.getCreateAddressRequest()));
        branch.setRestaurant(Restaurant.builder().id(request.getRestaurantId()).build());

        return branch;

    }
}
