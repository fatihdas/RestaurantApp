package com.restaurantapp.restapp.model.converter.dto.toentity;

import com.restaurantapp.restapp.model.dto.CityDto;
import com.restaurantapp.restapp.model.entity.City;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CityDtoToEntityConverter {

    private final CountyDtoToEntityConverter countyDtoToEntityConverter;

    public CityDtoToEntityConverter(@Lazy CountyDtoToEntityConverter countyDtoToEntityConverter) {
        this.countyDtoToEntityConverter = countyDtoToEntityConverter;
    }

    public City convert(CityDto cityDto) {

        return City.builder()
                .name(cityDto.getName())
                .build();
    }
}
