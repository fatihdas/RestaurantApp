package com.restaurantapp.restapp.service;

import com.restaurantapp.restapp.model.dto.AddressDto;
import com.restaurantapp.restapp.model.request.create.CreateAddressRequest;
import com.restaurantapp.restapp.model.request.update.UpdateAddressRequest;

import java.util.List;

public interface AddressService {

    AddressDto createAddress(CreateAddressRequest request);

    List<AddressDto> getAllAddresses();

    AddressDto getAddress(long id);

    AddressDto updateAddress(UpdateAddressRequest request, long id);

    void deleteAddress(long id);


}
