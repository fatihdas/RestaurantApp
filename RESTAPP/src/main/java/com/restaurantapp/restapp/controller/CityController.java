package com.restaurantapp.restapp.controller;

import com.restaurantapp.restapp.model.City;
import com.restaurantapp.restapp.service.CityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

<<<<<<< HEAD
@RestController
@RequestMapping("city")
=======
@RestController("branch")
>>>>>>> 09.08
public class CityController {

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @PostMapping
    public ResponseEntity<City> add(@RequestBody City city) {

        return new ResponseEntity<>(cityService.save(city), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<City>> getAll() {

        return new ResponseEntity<>(cityService.getAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<City> getById(@PathVariable long id) {

        return new ResponseEntity<>(cityService.getById(id), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<City> update(@RequestBody City city) {

        return new ResponseEntity<>(cityService.update(city), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {

        return new ResponseEntity(cityService.delete(id), HttpStatus.OK);
    }
}
