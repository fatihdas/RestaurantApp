package com.restaurantapp.restapp.controller;

import com.restaurantapp.restapp.model.dto.RestaurantDto;
import com.restaurantapp.restapp.model.request.create.CreateRestaurantRequest;
import com.restaurantapp.restapp.model.request.update.UpdateRestaurantRequest;
import com.restaurantapp.restapp.service.impl.RestaurantServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

<<<<<<< HEAD
@RestController
@RequestMapping("restaurant")
=======
@RestController("restaurant")
>>>>>>> 09.08
public class RestaurantController {

    private final RestaurantServiceImpl restaurantServiceImpl;

    public RestaurantController(RestaurantServiceImpl restaurantServiceImpl) {
        this.restaurantServiceImpl = restaurantServiceImpl;
    }

    @PostMapping
    public ResponseEntity<RestaurantDto> createRestaurant(@RequestBody CreateRestaurantRequest request) {

        return new ResponseEntity<>(restaurantServiceImpl.createRestaurant(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<RestaurantDto>> getAllRestaurants() {

        return new ResponseEntity<>(restaurantServiceImpl.getAllRestaurants(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<RestaurantDto> getRestaurant(@PathVariable long id) {

        return new ResponseEntity<>(restaurantServiceImpl.getRestaurant(id), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<RestaurantDto> updateRestaurant(@RequestBody UpdateRestaurantRequest request, @PathVariable long id) {

        return new ResponseEntity<>(restaurantServiceImpl.updateRestaurant(request, id), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {

        restaurantServiceImpl.deleteRestaurant(id);
        return ResponseEntity.ok().build();
    }
}
