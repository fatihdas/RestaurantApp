package com.restaurantapp.restapp.model.converter.dto.toentity;

import com.restaurantapp.restapp.model.dto.RestaurantDto;
import com.restaurantapp.restapp.model.entity.Restaurant;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class RestaurantDtoToEntityConverter {

    private final UserDtoToEntityConverter userDtoToEntityConverter;
    private final BranchDtoToEntityConverter branchDtoToEntityConverter;

    public RestaurantDtoToEntityConverter(UserDtoToEntityConverter userDtoToEntityConverter,
                                          BranchDtoToEntityConverter branchDtoToEntityConverter) {
        this.userDtoToEntityConverter = userDtoToEntityConverter;
        this.branchDtoToEntityConverter = branchDtoToEntityConverter;
    }

    public Restaurant convert(RestaurantDto restaurantDto) {

        return Restaurant.builder()
                .id(restaurantDto.getId())
                .name(restaurantDto.getName())
                .build();
    }
}
