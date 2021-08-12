package com.restaurantapp.restapp.model.converter.entity.todto;

import com.restaurantapp.restapp.model.dto.CountyDto;
import com.restaurantapp.restapp.model.entity.County;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class CountyEntityToDtoConverter {

    private final CityEntityToDtoConverter cityEntityToDtoConverter;

    public CountyEntityToDtoConverter(@Lazy CityEntityToDtoConverter cityEntityToDtoConverter) {
        this.cityEntityToDtoConverter = cityEntityToDtoConverter;
    }

    public CountyDto convert(County county){

        return CountyDto.builder()
                .id(county.getId())
                .name(county.getName())
                .build();
    }
}