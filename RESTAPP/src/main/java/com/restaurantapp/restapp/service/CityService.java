package com.restaurantapp.restapp.service;

import com.restaurantapp.restapp.model.dto.CityDto;

import java.util.List;

public interface CityService {

    List<CityDto> getAllCities();

    CityDto getCity(long id);

    void deleteCity(long id);
}
