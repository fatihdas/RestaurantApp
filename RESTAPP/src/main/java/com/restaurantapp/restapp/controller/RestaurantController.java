package com.restaurantapp.restapp.controller;

import com.restaurantapp.restapp.entity.Menu;
import com.restaurantapp.restapp.entity.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @PostMapping
    public ResponseEntity<Restaurant> add(@RequestBody Restaurant restaurant){

        return new ResponseEntity(restaurantService.save(item), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Restaurant>> getAll(){

        return new ResponseEntity(restaurantService.getAll(),HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Restaurant> getById(@PathVariable long id){

        return new ResponseEntity(restaurantService.getById(id),HttpStatus.OK)
    }

    @PutMapping
    public ResponseEntity<Restaurant> update(@RequestBody Restaurant restaurant){

        return new ResponseEntity(restaurantService.update(meal),HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Restaurant> delete(@PathVariable long id){
        return new ResponseEntity(restaurantService.delete(id),HttpStatus.OK);
    }
}
