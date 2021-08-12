package com.restaurantapp.restapp.service;

import com.restaurantapp.restapp.model.dto.CountyDto;
import com.restaurantapp.restapp.model.request.create.CreateCountyRequest;
import com.restaurantapp.restapp.model.request.update.UpdateCountyRequest;

import java.util.List;

public interface CountyService {

    CountyDto createCounty(CreateCountyRequest request);

    List<CountyDto> getAllCounties();

    CountyDto getCounty(long id);

    CountyDto updateCounty(UpdateCountyRequest request, long id);

    void deleteCounty(long id);
}
