package com.restaurantapp.restapp.service;

import com.restaurantapp.restapp.model.dto.CountyDto;
import com.restaurantapp.restapp.model.dto.RestaurantDto;
import com.restaurantapp.restapp.model.request.create.CreateRestaurantRequest;
import com.restaurantapp.restapp.model.request.update.UpdateRestaurantRequest;

import java.util.List;

public interface RestaurantService {

    RestaurantDto createRestaurant(CreateRestaurantRequest request);

    List<RestaurantDto> getAllRestaurants();

    RestaurantDto getRestaurant(long id);

//    List<RestaurantDto> getRestaurantsByCounty(CountyDto countyDto);

    RestaurantDto updateRestaurant(UpdateRestaurantRequest request, long id);

    void deleteRestaurant(long id);
}
