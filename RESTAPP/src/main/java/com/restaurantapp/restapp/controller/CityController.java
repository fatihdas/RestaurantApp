package com.restaurantapp.restapp.controller;

import com.restaurantapp.restapp.model.dto.CityDto;
import com.restaurantapp.restapp.model.request.create.CreateCityRequest;
import com.restaurantapp.restapp.model.request.update.UpdateCityRequest;
import com.restaurantapp.restapp.service.impl.CityServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("city")
public class CityController {

    private final CityServiceImpl cityServiceImpl;

    public CityController(CityServiceImpl cityServiceImpl) {
        this.cityServiceImpl = cityServiceImpl;
    }

    @PostMapping
    public ResponseEntity<CityDto> createCity(@RequestBody CreateCityRequest request) {

        return new ResponseEntity<>(cityServiceImpl.createCity(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CityDto>> getAllCities() {

        return new ResponseEntity<>(cityServiceImpl.getAllCities(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<CityDto> getById(@PathVariable long id) {

        return new ResponseEntity<>(cityServiceImpl.getCity(id), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<CityDto> updateCity(@RequestBody UpdateCityRequest request, @PathVariable long id) {

        return new ResponseEntity<>(cityServiceImpl.updateCity(request, id), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {

        cityServiceImpl.deleteCity(id);
        return ResponseEntity.ok().build();
    }
}
