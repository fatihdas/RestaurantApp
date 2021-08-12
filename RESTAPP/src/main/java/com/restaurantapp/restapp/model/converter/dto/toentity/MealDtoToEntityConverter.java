package com.restaurantapp.restapp.model.converter.dto.toentity;

import com.restaurantapp.restapp.model.dto.MealDto;
import com.restaurantapp.restapp.model.entity.Meal;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class MealDtoToEntityConverter {

    public Meal convert(MealDto mealDto) {

        return Meal.builder()
                .price(mealDto.getPrice())
                .name(mealDto.getName())
                .build();
    }
}
