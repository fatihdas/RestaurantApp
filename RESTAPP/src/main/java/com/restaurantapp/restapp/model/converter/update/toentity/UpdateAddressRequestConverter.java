package com.restaurantapp.restapp.model.converter.update.toentity;

import com.restaurantapp.restapp.model.converter.dto.toentity.CityDtoToEntityConverter;
import com.restaurantapp.restapp.model.converter.dto.toentity.CountyDtoToEntityConverter;
import com.restaurantapp.restapp.model.converter.dto.toentity.UserDtoToEntityConverter;
import com.restaurantapp.restapp.model.entity.Address;
import com.restaurantapp.restapp.model.request.update.UpdateAddressRequest;
import org.springframework.stereotype.Component;

@Component
public class UpdateAddressRequestConverter {

    private final CountyDtoToEntityConverter countyDtoToEntityConverter;
    private final CityDtoToEntityConverter cityDtoToEntityConverter;

    public UpdateAddressRequestConverter(CountyDtoToEntityConverter countyDtoToEntityConverter,
                                         CityDtoToEntityConverter cityDtoToEntityConverter) {
        this.countyDtoToEntityConverter = countyDtoToEntityConverter;
        this.cityDtoToEntityConverter = cityDtoToEntityConverter;
    }

    public Address convert(UpdateAddressRequest request){

        return Address.builder()
                .county(countyDtoToEntityConverter.convert(request.getCountyDto()))
                .city(cityDtoToEntityConverter.convert(request.getCityDto()))
                .content(request.getContent())
                .build();
    }
}
