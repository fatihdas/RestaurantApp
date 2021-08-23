package com.restaurantapp.restapp.model.converter.dto.toentity;

import com.restaurantapp.restapp.model.dto.CityDto;
import com.restaurantapp.restapp.model.entity.City;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CityDtoToEntityConverter {


    public City convert(CityDto cityDto) {

        return City.builder()
                .id(cityDto.getId())
                .name(cityDto.getName())
                .build();
    }
}
