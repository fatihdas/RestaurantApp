package com.restaurantapp.restapp.model.converter.dto.toentity;

import com.restaurantapp.restapp.model.dto.AddressDto;
import com.restaurantapp.restapp.model.entity.Address;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class AddressDtoToEntityConverter {

    private final BranchDtoToEntityConverter branchDtoToEntityConverter;
    private final UserDtoToEntityConverter userDtoToEntityConverter;
    private final CityDtoToEntityConverter cityDtoToEntityConverter;
    private final CountyDtoToEntityConverter countyDtoToEntityConverter;

    public AddressDtoToEntityConverter(@Lazy BranchDtoToEntityConverter branchDtoToEntityConverter,
                                       @Lazy UserDtoToEntityConverter userDtoToEntityConverter,
                                       @Lazy CityDtoToEntityConverter cityDtoToEntityConverter,
                                       @Lazy CountyDtoToEntityConverter countyDtoToEntityConverter) {
        this.branchDtoToEntityConverter = branchDtoToEntityConverter;
        this.userDtoToEntityConverter = userDtoToEntityConverter;
        this.cityDtoToEntityConverter = cityDtoToEntityConverter;
        this.countyDtoToEntityConverter = countyDtoToEntityConverter;
    }

    public Address convert(AddressDto addressDto){

        return Address.builder()
                .content(addressDto.getContent())
                .city(cityDtoToEntityConverter.convert(addressDto.getCityDto()))
                .county(countyDtoToEntityConverter.convert(addressDto.getCountyDto()))
                .build();
    }
}
