package com.restaurantapp.restapp.model.converter.entity.todto;

import com.restaurantapp.restapp.model.dto.CountyDto;
import com.restaurantapp.restapp.model.entity.County;
import org.springframework.stereotype.Component;

@Component
public class CountyEntityToDtoConverter {

    public CountyDto convert(County county) {
        CountyDto countyDto = new CountyDto();
        countyDto.setCityId(county.getCity().getId());
        countyDto.setId(county.getId());
        countyDto.setName(county.getName());

        return countyDto;
    }
}
