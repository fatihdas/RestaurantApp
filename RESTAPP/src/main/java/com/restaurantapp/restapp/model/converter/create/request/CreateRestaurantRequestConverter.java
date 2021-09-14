package com.restaurantapp.restapp.model.converter.create.request;

import com.restaurantapp.restapp.model.entity.Branch;
import com.restaurantapp.restapp.model.entity.Restaurant;
import com.restaurantapp.restapp.model.entity.User;
import com.restaurantapp.restapp.model.request.create.CreateRestaurantRequest;
import com.restaurantapp.restapp.service.impl.UserServiceImpl;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class CreateRestaurantRequestConverter {

    public Restaurant convert(CreateRestaurantRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("invalid request!");
        }
        Restaurant restaurant = new Restaurant();
        User user = new User();
        user.setId(request.getUserId());
        restaurant.setUser(user);
        restaurant.setName(request.getName());
        restaurant.setId(request.getId());
        return restaurant;
    }
}
