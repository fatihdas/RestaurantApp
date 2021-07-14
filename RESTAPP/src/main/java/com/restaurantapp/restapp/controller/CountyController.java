package com.restaurantapp.restapp.controller;

import com.restaurantapp.restapp.model.County;
import com.restaurantapp.restapp.service.CountyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("county")
public class CountyController {

    private final CountyService countyService;

    public CountyController(CountyService countyService) {
        this.countyService = countyService;
    }

    @PostMapping
    public ResponseEntity<County> add(@RequestBody County county){

        return new ResponseEntity(countyService.save(county), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<County>> getAll(){

        return new ResponseEntity(countyService.getAll(),HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<County> getById(@PathVariable long id){

        return new ResponseEntity(countyService.getById(id),HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<County> update(@RequestBody County county, @PathVariable long id){

        return new ResponseEntity(countyService.update(county, id),HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<County> delete(@PathVariable long id){
        return new ResponseEntity(countyService.delete(id),HttpStatus.OK);
    }
}
