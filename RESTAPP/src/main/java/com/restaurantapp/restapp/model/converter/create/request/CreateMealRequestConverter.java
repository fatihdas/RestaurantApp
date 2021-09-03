package com.restaurantapp.restapp.model.converter.create.request;

import com.restaurantapp.restapp.model.entity.Meal;
import com.restaurantapp.restapp.model.entity.Menu;
import com.restaurantapp.restapp.model.request.create.CreateMealRequest;
import org.springframework.stereotype.Component;

@Component
public class CreateMealRequestConverter {

    public Meal convert(CreateMealRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("invalid request!");
        }
        Meal meal = new Meal();
        meal.setPrice(request.getPrice());
        meal.setName(request.getName());
        meal.setId(request.getId());
        meal.setMenu(Menu.builder().id(request.getMenuId()).build());

        return meal;
    }
}
