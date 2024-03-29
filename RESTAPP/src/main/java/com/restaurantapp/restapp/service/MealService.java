package com.restaurantapp.restapp.service;

import com.restaurantapp.restapp.model.dto.MealDto;
import com.restaurantapp.restapp.model.request.create.CreateMealRequest;
import com.restaurantapp.restapp.model.request.update.UpdateMealRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface MealService {

    MealDto createMeal(CreateMealRequest request) throws Exception;

    List<MealDto> getAllMeals();

    MealDto getMeal(long id);

    List<MealDto> getMealByBranchId(long branchId);

    String updateMeal(UpdateMealRequest request, long id);

    void deleteMeal(long id);

}
