package com.restaurantapp.restapp.service;

import com.restaurantapp.restapp.model.dto.CountyDto;
import com.restaurantapp.restapp.model.entity.County;

import java.util.List;

public interface CountyService {

    List<CountyDto> getAllCounties();

    CountyDto getCountyDto(long id);

    County getCounty(long id);

    void deleteCounty(long id);
}
