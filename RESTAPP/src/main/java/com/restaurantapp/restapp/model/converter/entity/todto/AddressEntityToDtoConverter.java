package com.restaurantapp.restapp.model.converter.entity.todto;

import com.restaurantapp.restapp.model.dto.AddressDto;
import com.restaurantapp.restapp.model.entity.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressEntityToDtoConverter {

    public AddressDto convert(Address address) {
        AddressDto addressDto = new AddressDto();
        addressDto.setId(address.getId());
        addressDto.setContent(addressDto.getContent());
        addressDto.setBranchId(address.getBranch().getId());
        addressDto.setCountyId(address.getCounty().getId());
        addressDto.setCountyName(address.getCounty().getName());
        addressDto.setUserId(address.getUser().getId());

        return addressDto;
    }
}
