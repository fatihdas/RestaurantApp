package com.restaurantapp.restapp.controller;

import com.restaurantapp.restapp.model.dto.MealDto;
import com.restaurantapp.restapp.model.entity.Meal;
import com.restaurantapp.restapp.model.request.create.CreateMealRequest;
import com.restaurantapp.restapp.service.impl.MealServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("meal")
public class MealController {

    private final MealServiceImpl mealService;

    public MealController(MealServiceImpl mealService) {
        this.mealService = mealService;
    }

    @PostMapping
    public ResponseEntity<MealDto> createMeal(@RequestBody CreateMealRequest request) {
        return new ResponseEntity<>(mealService.createMeal(request), HttpStatus.OK);
    }

    @GetMapping("/{branchId}")
    public ResponseEntity<List<MealDto>> getMealByBranchId(@PathVariable long branchId){
        return new ResponseEntity<>(mealService.getMealByBranchId(branchId), HttpStatus.OK);
    }

}
