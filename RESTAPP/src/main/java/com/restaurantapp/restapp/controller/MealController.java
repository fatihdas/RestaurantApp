package com.restaurantapp.restapp.controller;

import com.restaurantapp.restapp.model.Meal;
import com.restaurantapp.restapp.service.MealService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("meal")
public class MealController {

    private final MealService mealService;

    public MealController(MealService mealService) {
        this.mealService = mealService;
    }

    @PostMapping
    public ResponseEntity<Meal> add(@RequestBody Meal meal){

        return new ResponseEntity(mealService.save(meal), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Meal>> getAll(){

        return new ResponseEntity(mealService.getAll(),HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Meal> getById(@PathVariable long id){

        return new ResponseEntity(mealService.getById(id),HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Meal> update(@RequestBody Meal meal, @PathVariable long id){

        return new ResponseEntity(mealService.update(meal, id),HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Meal> delete(@PathVariable long id){
        return new ResponseEntity(mealService.delete(id),HttpStatus.OK);
    }
}
