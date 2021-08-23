package com.restaurantapp.restapp.model.converter.entity.todto;

import com.restaurantapp.restapp.model.dto.RestaurantDto;
import com.restaurantapp.restapp.model.entity.Restaurant;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class RestaurantEntityToDtoConverter {

    private final BranchEntityToDtoConverter branchEntityToDtoConverter;
    private final UserEntityToDtoConverter userEntityToDtoConverter;

    public RestaurantEntityToDtoConverter(BranchEntityToDtoConverter branchEntityToDtoConverter, UserEntityToDtoConverter userEntityToDtoConverter) {
        this.branchEntityToDtoConverter = branchEntityToDtoConverter;
        this.userEntityToDtoConverter = userEntityToDtoConverter;
    }

    public RestaurantDto convert(Restaurant restaurant) {

        return RestaurantDto.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .branchDtoList(restaurant.getBranchList().stream().map(branchEntityToDtoConverter::convert)
                        .collect(Collectors.toList()))
                .userDto(userEntityToDtoConverter.convert(restaurant.getUser()))
                .build();
    }
}
