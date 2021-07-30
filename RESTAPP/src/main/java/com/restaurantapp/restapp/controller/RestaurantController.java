package com.restaurantapp.restapp.controller;

import com.restaurantapp.restapp.model.Restaurant;
import com.restaurantapp.restapp.service.RestaurantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("restaurant")
public class RestaurantController {

    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @PostMapping
    public ResponseEntity<Restaurant> add(@RequestBody Restaurant restaurant) {

        return new ResponseEntity<>(restaurantService.save(restaurant), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Restaurant>> getAll() {

        return new ResponseEntity<>(restaurantService.getAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Restaurant> getById(@PathVariable long id) {

        return new ResponseEntity<>(restaurantService.getById(id), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Restaurant> update(@RequestBody Restaurant restaurant) {

        return new ResponseEntity<>(restaurantService.update(restaurant), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {

        return new ResponseEntity(restaurantService.delete(id), HttpStatus.OK);
    }
}
