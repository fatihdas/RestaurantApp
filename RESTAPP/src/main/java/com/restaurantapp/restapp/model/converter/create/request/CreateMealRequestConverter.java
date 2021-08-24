package com.restaurantapp.restapp.model.converter.create.request;

import com.restaurantapp.restapp.model.entity.Meal;
import com.restaurantapp.restapp.model.request.create.CreateMealRequest;
import org.springframework.stereotype.Component;

@Component
public class CreateMealRequestConverter {

    public Meal convert(CreateMealRequest request) {

        return Meal.builder()
                .id(request.getId())
                .name(request.getName())
                .price(request.getPrice())
                .build();
    }
}