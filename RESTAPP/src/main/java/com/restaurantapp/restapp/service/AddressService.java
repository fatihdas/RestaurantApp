package com.restaurantapp.restapp.service;

import com.restaurantapp.restapp.model.dto.AddressDto;
import com.restaurantapp.restapp.model.request.create.CreateAddressRequest;

import java.util.List;

public interface AddressService {

    AddressDto createAddress(CreateAddressRequest request);

    AddressDto getAddress(long id);

//    void deleteAddress(long id);

    List<AddressDto> getUserAdresses(long userId);

}
