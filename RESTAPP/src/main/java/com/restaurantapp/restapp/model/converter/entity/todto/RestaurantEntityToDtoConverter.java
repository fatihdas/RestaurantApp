package com.restaurantapp.restapp.model.converter.entity.todto;

import com.restaurantapp.restapp.model.dto.RestaurantDto;
import com.restaurantapp.restapp.model.entity.Restaurant;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class RestaurantEntityToDtoConverter {

    private final UserEntityToDtoConverter userEntityToDtoConverter;

    public RestaurantEntityToDtoConverter(@Lazy UserEntityToDtoConverter userEntityToDtoConverter) {
        this.userEntityToDtoConverter = userEntityToDtoConverter;
    }

    public RestaurantDto convert(Restaurant restaurant) {

        return RestaurantDto.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .build();
    }
}
