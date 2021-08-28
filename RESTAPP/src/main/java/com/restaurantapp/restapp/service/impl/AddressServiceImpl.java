package com.restaurantapp.restapp.service.impl;

import com.restaurantapp.restapp.exception.AddressNotFoundException;
import com.restaurantapp.restapp.model.converter.create.request.CreateAddressRequestConverter;
import com.restaurantapp.restapp.model.converter.entity.todto.AddressEntityToDtoConverter;
import com.restaurantapp.restapp.model.dto.AddressDto;
import com.restaurantapp.restapp.model.dto.UserDto;
import com.restaurantapp.restapp.model.entity.Address;
import com.restaurantapp.restapp.model.entity.User;
import com.restaurantapp.restapp.model.request.create.CreateAddressRequest;
import com.restaurantapp.restapp.model.request.update.UpdateAddressRequest;
import com.restaurantapp.restapp.repository.AddressRepository;
import com.restaurantapp.restapp.service.AddressService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final AddressEntityToDtoConverter addressEntityToDtoConverter;
    private final CreateAddressRequestConverter createAddressRequestConverter;
    private final UserServiceImpl userService;

    public AddressServiceImpl(AddressRepository addressRepository,
                              AddressEntityToDtoConverter addressEntityToDtoConverter,
                              CreateAddressRequestConverter createAddressRequestConverter,
                              UserServiceImpl userService) {
        this.addressRepository = addressRepository;
        this.addressEntityToDtoConverter = addressEntityToDtoConverter;
        this.createAddressRequestConverter = createAddressRequestConverter;
        this.userService = userService;
    }

    public AddressDto createAddress(CreateAddressRequest request) {

        return addressEntityToDtoConverter.convert(addressRepository.save(createAddressRequestConverter.convert(request)));
    }

    public AddressDto getAddress(long id) {

        return addressEntityToDtoConverter.convert(addressRepository.findById(id)
                .orElseThrow(() -> new AddressNotFoundException(id)));
    }

    public List<AddressDto> getUserAdresses(long userId){
        UserDto user = userService.getUser(userId);
        return user.getAddressDtoList();
    }

    public String updateAddress(UpdateAddressRequest request, long id) {

        Address address = addressRepository.findById(id).orElseThrow(() -> new AddressNotFoundException());

        address.setCityName(request.getCityName());
        address.setContent(request.getContent());
        address.setCountyName(request.getCountyName());

        return "Address has been updated! id:" + id;
    }

    public void deleteAddress(long id) {

        addressRepository.deleteById(id);
    }

}
