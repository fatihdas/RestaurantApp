package com.restaurantapp.restapp.model.converter.create.request.toentity;

import com.restaurantapp.restapp.model.converter.dto.toentity.CityDtoToEntityConverter;
import com.restaurantapp.restapp.model.converter.dto.toentity.CountyDtoToEntityConverter;
import com.restaurantapp.restapp.model.converter.dto.toentity.UserDtoToEntityConverter;
import com.restaurantapp.restapp.model.entity.Address;
import com.restaurantapp.restapp.model.request.create.CreateAddressRequest;
import org.springframework.stereotype.Component;

@Component
public class CreateAddressRequestConverter {

    private final UserDtoToEntityConverter userDtoToEntityConverter;
    private final CountyDtoToEntityConverter countyDtoToEntityConverter;
    private final CityDtoToEntityConverter cityDtoToEntityConverter;

    public CreateAddressRequestConverter(UserDtoToEntityConverter userDtoToEntityConverter,
                                         CountyDtoToEntityConverter countyDtoToEntityConverter,
                                         CityDtoToEntityConverter cityDtoToEntityConverter) {
        this.userDtoToEntityConverter = userDtoToEntityConverter;
        this.countyDtoToEntityConverter = countyDtoToEntityConverter;
        this.cityDtoToEntityConverter = cityDtoToEntityConverter;
    }

    public Address convert(CreateAddressRequest request) {

        return Address.builder()
                .city(cityDtoToEntityConverter.convert(request.getCityDto()))
                .county(countyDtoToEntityConverter.convert(request.getCountyDto()))
                .content(request.getContent())
                .build();
    }
}
