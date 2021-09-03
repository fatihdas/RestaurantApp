package com.restaurantapp.restapp.model.converter.entity.todto;

import com.restaurantapp.restapp.model.dto.MealDto;
import com.restaurantapp.restapp.model.entity.Meal;
import org.springframework.stereotype.Component;

@Component
public class MealEntityToDtoConverter {

    public MealDto convert(Meal meal) {
        MealDto mealDto = new MealDto();
        mealDto.setId(meal.getId());
        mealDto.setMenuId(meal.getMenu().getId());
        mealDto.setPrice(meal.getPrice());
        mealDto.setName(meal.getName());

        return mealDto;
    }
}
