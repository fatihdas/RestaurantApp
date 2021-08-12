package com.restaurantapp.restapp.controller;

import com.restaurantapp.restapp.model.dto.CountyDto;
import com.restaurantapp.restapp.model.request.create.CreateCountyRequest;
import com.restaurantapp.restapp.model.request.update.UpdateCountyRequest;
import com.restaurantapp.restapp.service.impl.CountyServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("county")
public class CountyController {

    private final CountyServiceImpl countyServiceImpl;

    public CountyController(CountyServiceImpl countyServiceImpl) {
        this.countyServiceImpl = countyServiceImpl;
    }

    @PostMapping
    public ResponseEntity<CountyDto> createCounty(@RequestBody CreateCountyRequest request) {

        return new ResponseEntity<>(countyServiceImpl.createCounty(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CountyDto>> getAllCounties() {

        return new ResponseEntity<>(countyServiceImpl.getAllCounties(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<CountyDto> getCounty(@PathVariable long id) {

        return new ResponseEntity<>(countyServiceImpl.getCounty(id), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<CountyDto> updateCounty(@RequestBody UpdateCountyRequest request, @PathVariable long id) {

        return new ResponseEntity<>(countyServiceImpl.updateCounty(request, id), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCounty(@PathVariable long id) {

        countyServiceImpl.deleteCounty(id);
        return ResponseEntity.ok().build();
    }
}
