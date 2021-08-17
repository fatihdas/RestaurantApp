package com.restaurantapp.restapp.model.converter.dto.toentity;

import com.restaurantapp.restapp.model.dto.AddressDto;
import com.restaurantapp.restapp.model.entity.Address;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class AddressDtoToEntityConverter {

    public Address convert(AddressDto addressDto){

        return Address.builder()
                .id(addressDto.getId())
                .content(addressDto.getContent())
                .cityName(addressDto.getCityName())
                .countyName(addressDto.getCountyName())
                .content(addressDto.getContent())
                .build();
    }
}
