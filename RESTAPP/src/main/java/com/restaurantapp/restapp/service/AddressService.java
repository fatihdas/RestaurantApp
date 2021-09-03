package com.restaurantapp.restapp.service;

import com.restaurantapp.restapp.model.dto.AddressDto;
import com.restaurantapp.restapp.model.dto.UserDto;
import com.restaurantapp.restapp.model.request.create.CreateAddressRequest;
import com.restaurantapp.restapp.model.request.update.UpdateAddressRequest;

import java.util.List;

public interface AddressService {

    AddressDto createAddress(CreateAddressRequest request);

    AddressDto getAddress(long id);

    String updateAddress(UpdateAddressRequest request, long id);

    void deleteAddress(long id);

    List<AddressDto> getUserAdresses(long userId);

}
