package com.restaurantapp.restapp.controller;

import com.restaurantapp.restapp.model.dto.MealDto;
import com.restaurantapp.restapp.model.request.create.CreateMealRequest;
import com.restaurantapp.restapp.model.request.update.UpdateMealRequest;
import com.restaurantapp.restapp.service.impl.MealServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

<<<<<<< HEAD
@RestController
@RequestMapping("meal")
=======
@RestController("meal")
>>>>>>> 09.08
public class MealController {

    private final MealServiceImpl mealServiceImpl;

    public MealController(MealServiceImpl mealServiceImpl) {
        this.mealServiceImpl = mealServiceImpl;
    }

    @PostMapping
    public ResponseEntity<MealDto> createMeal(@RequestBody CreateMealRequest request) {

        return new ResponseEntity<>(mealServiceImpl.createMeal(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<MealDto>> getAllMeals() {

        return new ResponseEntity<>(mealServiceImpl.getAllMeals(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<MealDto> getMeal(@PathVariable long id) {

        return new ResponseEntity<>(mealServiceImpl.getMeal(id), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<MealDto> update(@RequestBody UpdateMealRequest request, @PathVariable long id) {

        return new ResponseEntity<>(mealServiceImpl.updateMeal(request,id), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {

        mealServiceImpl.deleteMeal(id);
        return ResponseEntity.ok().build();
    }
}
