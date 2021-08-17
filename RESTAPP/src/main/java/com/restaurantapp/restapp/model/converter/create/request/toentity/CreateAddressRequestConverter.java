package com.restaurantapp.restapp.model.converter.create.request.toentity;

import com.restaurantapp.restapp.model.converter.dto.toentity.CityDtoToEntityConverter;
import com.restaurantapp.restapp.model.converter.dto.toentity.CountyDtoToEntityConverter;
import com.restaurantapp.restapp.model.converter.dto.toentity.UserDtoToEntityConverter;
import com.restaurantapp.restapp.model.entity.Address;
import com.restaurantapp.restapp.model.request.create.CreateAddressRequest;
import org.springframework.stereotype.Component;

@Component
public class CreateAddressRequestConverter {

    public Address convert(CreateAddressRequest request) {

        return Address.builder()
                .cityName(request.getCityName())
                .countyName(request.getCountyName())
                .content(request.getContent())
                .build();
    }
}
