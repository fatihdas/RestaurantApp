package com.restaurantapp.restapp.controller;

import com.restaurantapp.restapp.entity.Branch;
import com.restaurantapp.restapp.entity.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class CityController {

    @Autowired
    private CityService cityService;

    @PostMapping
    public ResponseEntity<City> add(@RequestBody City city){

        return new ResponseEntity(cityService.save(basket), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<City>> getAll(){

        return new ResponseEntity(cityService.getAll(),HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<City> getById(@PathVariable long id){

        return new ResponseEntity(cityService.getById(id),HttpStatus.OK)
    }

    @PutMapping
    public ResponseEntity<City> update(@RequestBody City city){

        return new ResponseEntity(cityService.update(city),HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<City> delete(@PathVariable long id){
        return new ResponseEntity(cityService.delete(id),HttpStatus.OK);
    }
}
