package com.restaurantapp.restapp.model.converter.update.toentity;

import com.restaurantapp.restapp.model.entity.Meal;
import com.restaurantapp.restapp.model.request.update.UpdateMealRequest;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UpdateMealRequestConverter {


    public Meal convert(UpdateMealRequest request) {

        return Meal.builder()
                .name(request.getName())
                .price(request.getPrice())
                .build();
    }
}
