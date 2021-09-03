package com.restaurantapp.restapp.model.converter.entity.todto;

import com.restaurantapp.restapp.model.dto.CityDto;
import com.restaurantapp.restapp.model.entity.City;
import org.springframework.stereotype.Component;

@Component
public class CityEntityToDtoConverter {

    public CityDto convert(City city) {
        CityDto cityDto = new CityDto();
        cityDto.setId(city.getId());
        cityDto.setName(city.getName());

        return cityDto;
    }
}
