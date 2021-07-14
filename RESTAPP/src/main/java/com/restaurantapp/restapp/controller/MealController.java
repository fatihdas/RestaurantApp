package com.restaurantapp.restapp.controller;

import com.restaurantapp.restapp.entity.Item;
import com.restaurantapp.restapp.entity.Meal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class MealController {

    @Autowired
    private MealService mealService;

    @PostMapping
    public ResponseEntity<Meal> add(@RequestBody Meal meal){

        return new ResponseEntity(mealService.save(item), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Meal>> getAll(){

        return new ResponseEntity(mealService.getAll(),HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Meal> getById(@PathVariable long id){

        return new ResponseEntity(mealService.getById(id),HttpStatus.OK)
    }

    @PutMapping
    public ResponseEntity<Meal> update(@RequestBody Meal meal){

        return new ResponseEntity(mealService.update(meal),HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Meal> delete(@PathVariable long id){
        return new ResponseEntity(mealService.delete(id),HttpStatus.OK);
    }
}
