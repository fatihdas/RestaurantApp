package com.restaurantapp.restapp.model.converter.entity.todto;

import com.restaurantapp.restapp.model.dto.MealDto;
import com.restaurantapp.restapp.model.entity.Meal;
import org.springframework.stereotype.Component;

@Component
public class MealEntityToDtoConverter {

    public MealDto convert(Meal meal) {

        return MealDto.builder()
                .id(meal.getId())
                .name(meal.getName())
                .price(meal.getPrice())
                .build();
    }
}
