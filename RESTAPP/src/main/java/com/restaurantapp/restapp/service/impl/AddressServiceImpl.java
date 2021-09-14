package com.restaurantapp.restapp.service.impl;

import com.restaurantapp.restapp.exception.AddressNotFoundException;
import com.restaurantapp.restapp.model.converter.create.request.CreateAddressRequestConverter;
import com.restaurantapp.restapp.model.converter.entity.todto.AddressEntityToDtoConverter;
import com.restaurantapp.restapp.model.dto.AddressDto;
import com.restaurantapp.restapp.model.entity.Address;
import com.restaurantapp.restapp.model.request.create.CreateAddressRequest;
import com.restaurantapp.restapp.model.request.update.UpdateAddressRequest;
import com.restaurantapp.restapp.repository.AddressRepository;
import com.restaurantapp.restapp.service.AddressService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final AddressEntityToDtoConverter addressEntityToDtoConverter;
    private final CreateAddressRequestConverter createAddressRequestConverter;

    public AddressServiceImpl(AddressRepository addressRepository,
                              AddressEntityToDtoConverter addressEntityToDtoConverter,
                              CreateAddressRequestConverter createAddressRequestConverter) {
        this.addressRepository = addressRepository;
        this.addressEntityToDtoConverter = addressEntityToDtoConverter;
        this.createAddressRequestConverter = createAddressRequestConverter;
    }

    public AddressDto createAddress(CreateAddressRequest request) {

        Address address = addressRepository.save(createAddressRequestConverter.convert(request));
        return addressEntityToDtoConverter.convert(address);
    }

    public AddressDto getAddress(long id) {

        Address address = addressRepository.findById(id).orElseThrow(() -> new AddressNotFoundException(id));
        return addressEntityToDtoConverter.convert(address);
    }
//
//    public void deleteAddress(long id) {
//
//        addressRepository.deleteById(id);
//    }

    @Override
    public List<AddressDto> getUserAdresses(long userId) {

        List<Address> addressList = addressRepository.findAllByUserId(userId);
        return addressList.stream().map(addressEntityToDtoConverter::convert).collect(Collectors.toList());
    }

}
