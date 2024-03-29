package com.restaurantapp.restapp.service;

import com.restaurantapp.restapp.model.dto.RestaurantDto;
import com.restaurantapp.restapp.model.entity.Meal;
import com.restaurantapp.restapp.model.entity.Restaurant;
import com.restaurantapp.restapp.model.request.create.CreateRestaurantRequest;
import com.restaurantapp.restapp.model.request.update.UpdateRestaurantRequest;

import java.util.List;

public interface RestaurantService {

    RestaurantDto createRestaurant(CreateRestaurantRequest request) throws Exception;

    List<RestaurantDto> getAllRestaurants();

    RestaurantDto getRestaurantDto(long id);

    Restaurant getRestaurant(long id);

//    List<RestaurantDto> getRestaurantsByCounty(CountyDto countyDto);

    String updateRestaurant(UpdateRestaurantRequest request, long id);

    void deleteRestaurant(long id);

}
