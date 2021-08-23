package com.restaurantapp.restapp.model.converter.entity.todto;

import com.restaurantapp.restapp.model.dto.AddressDto;
import com.restaurantapp.restapp.model.entity.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressEntityToDtoConverter {


    public AddressDto convert(Address address) {

        return AddressDto.builder()
                .id(address.getId())
                .cityName(address.getCityName())
                .countyName(address.getCountyName())
                .content(address.getContent())
                .build();
    }
}
