package com.restaurantapp.restapp.model.converter.dto.toentity;

import com.restaurantapp.restapp.model.dto.CountyDto;
import com.restaurantapp.restapp.model.entity.County;
import org.springframework.stereotype.Component;

@Component
public class CountyDtoToEntityConverter {

    public County convert(CountyDto countyDto) {

        return County.builder()
                .name(countyDto.getName())
                .build();
    }
}
