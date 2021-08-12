package com.restaurantapp.restapp.service;

import com.restaurantapp.restapp.model.dto.MealDto;
import com.restaurantapp.restapp.model.request.create.CreateMealRequest;
import com.restaurantapp.restapp.model.request.update.UpdateMealRequest;

import java.util.List;

public interface MealService {

    MealDto createMeal(CreateMealRequest request);

    List<MealDto> getAllMeals();

    MealDto getMeal(long id);

    MealDto updateMeal(UpdateMealRequest request, long id);

    void deleteMeal(long id);
}
