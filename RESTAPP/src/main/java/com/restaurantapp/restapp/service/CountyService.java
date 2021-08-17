package com.restaurantapp.restapp.service;

import com.restaurantapp.restapp.model.dto.CountyDto;

import java.util.List;

public interface CountyService {

    List<CountyDto> getAllCounties();

    CountyDto getCounty(long id);

    void deleteCounty(long id);
}
