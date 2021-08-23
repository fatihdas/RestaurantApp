package com.restaurantapp.restapp.model.converter.update.toentity;

import com.restaurantapp.restapp.model.converter.dto.toentity.CityDtoToEntityConverter;
import com.restaurantapp.restapp.model.entity.Address;
import com.restaurantapp.restapp.model.request.update.UpdateAddressRequest;
import org.springframework.stereotype.Component;

@Component
public class UpdateAddressRequestConverter {

    public Address convert(UpdateAddressRequest request){

        return Address.builder()
                .countyName(request.getCountyName())
                .cityName(request.getCityName())
                .content(request.getContent())
                .build();
    }
}
