package com.restaurantapp.restapp.model.converter.create.request;

import com.restaurantapp.restapp.model.entity.Address;
import com.restaurantapp.restapp.model.request.create.CreateAddressRequest;
import org.springframework.stereotype.Component;

@Component
public class CreateAddressRequestConverter {

    public Address convert(CreateAddressRequest request) {

        return Address.builder()
                .id(request.getId())
                .cityName(request.getCityName())
                .countyName(request.getCountyName())
                .content(request.getContent())
                .build();
    }
}
