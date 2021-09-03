package com.restaurantapp.restapp.model.converter.create.request;

import com.restaurantapp.restapp.model.entity.Branch;
import com.restaurantapp.restapp.model.entity.Restaurant;
import com.restaurantapp.restapp.model.entity.User;
import com.restaurantapp.restapp.model.request.create.CreateRestaurantRequest;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class CreateRestaurantRequestConverter {

    private final CreateBranchRequestConverter createBranchRequestConverter;

    public CreateRestaurantRequestConverter(CreateBranchRequestConverter createBranchRequestConverter) {
        this.createBranchRequestConverter = createBranchRequestConverter;
    }

    public Restaurant convert(CreateRestaurantRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("invalid request!");
        }
        Restaurant restaurant = new Restaurant();
        restaurant.setUser(User.builder().id(request.getUserId()).build());
        restaurant.setName(restaurant.getName());
        restaurant.setId(restaurant.getId());

        Branch branch = createBranchRequestConverter.convert(request.getCreateBranchRequest());
        restaurant.setBranchList(Collections.singletonList(branch));
        return restaurant;
    }
}
