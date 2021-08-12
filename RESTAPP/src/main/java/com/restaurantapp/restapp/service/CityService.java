package com.restaurantapp.restapp.service;

import com.restaurantapp.restapp.model.dto.CityDto;
import com.restaurantapp.restapp.model.request.create.CreateCityRequest;
import com.restaurantapp.restapp.model.request.update.UpdateCityRequest;

import java.util.List;

public interface CityService {

    CityDto createCity(CreateCityRequest request);

    List<CityDto> getAllCities();

    CityDto getCity(long id);

    CityDto updateCity(UpdateCityRequest request, long id);

    void deleteCity(long id);
}
