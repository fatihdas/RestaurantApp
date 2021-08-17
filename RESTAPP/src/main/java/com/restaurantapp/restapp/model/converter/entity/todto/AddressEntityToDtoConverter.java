package com.restaurantapp.restapp.model.converter.entity.todto;

import com.restaurantapp.restapp.model.dto.AddressDto;
import com.restaurantapp.restapp.model.entity.Address;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class AddressEntityToDtoConverter {

    private final CityEntityToDtoConverter cityEntityToDtoConverter;
    private final CountyEntityToDtoConverter countyEntityToDtoConverter;
    private final UserEntityToDtoConverter userEntityToDtoConverter;

    public AddressEntityToDtoConverter(@Lazy CityEntityToDtoConverter cityEntityToDtoConverter,
                                       @Lazy CountyEntityToDtoConverter countyEntityToDtoConverter,
                                       @Lazy UserEntityToDtoConverter userEntityToDtoConverter) {
        this.cityEntityToDtoConverter = cityEntityToDtoConverter;
        this.countyEntityToDtoConverter = countyEntityToDtoConverter;
        this.userEntityToDtoConverter = userEntityToDtoConverter;
    }

    public AddressDto convert(Address address){

        return AddressDto.builder()
                .id(address.getId())
                .content(address.getContent())
                .build();
    }
}
