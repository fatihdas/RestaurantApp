package com.restaurantapp.restapp.model.converter.entity.todto;

import com.restaurantapp.restapp.model.dto.RestaurantDto;
import com.restaurantapp.restapp.model.entity.Restaurant;
import org.springframework.stereotype.Component;

@Component
public class RestaurantEntityToDtoConverter {

    public RestaurantDto convert(Restaurant restaurant) {
        RestaurantDto restaurantDto = new RestaurantDto();
        restaurantDto.setId(restaurant.getId());
        restaurantDto.setName(restaurant.getName());
        restaurantDto.setUserId(restaurant.getUser().getId());

        return restaurantDto;
    }
}
