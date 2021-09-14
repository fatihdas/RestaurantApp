package com.restaurantapp.restapp.service;

import com.restaurantapp.restapp.model.dto.CityDto;
import com.restaurantapp.restapp.model.entity.City;

import java.util.List;

public interface CityService {

    List<CityDto> getAllCities();

    CityDto getCityDto(long id);

    City getCity(long id);

}
