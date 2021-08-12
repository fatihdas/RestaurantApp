package com.restaurantapp.restapp.service.impl;

import com.restaurantapp.restapp.exception.AddressNotFoundException;
import com.restaurantapp.restapp.model.converter.create.request.toentity.CreateAddressRequestConverter;
import com.restaurantapp.restapp.model.converter.update.toentity.UpdateAddressRequestConverter;
import com.restaurantapp.restapp.model.dto.AddressDto;
import com.restaurantapp.restapp.model.converter.entity.todto.AddressEntityToDtoConverter;
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
    private final UpdateAddressRequestConverter updateAddressRequestConverter;

    public AddressServiceImpl(AddressRepository addressRepository, AddressEntityToDtoConverter addressEntityToDtoConverter,
                              CreateAddressRequestConverter createAddressRequestConverter, UpdateAddressRequestConverter updateAddressRequestConverter) {
        this.addressRepository = addressRepository;
        this.addressEntityToDtoConverter = addressEntityToDtoConverter;
        this.createAddressRequestConverter = createAddressRequestConverter;
        this.updateAddressRequestConverter = updateAddressRequestConverter;
    }

    public AddressDto createAddress(CreateAddressRequest request) {

        return addressEntityToDtoConverter.convert(addressRepository.save(createAddressRequestConverter.convert(request)));
    }

    public List<AddressDto> getAllAddresses() {

        return addressRepository.findAll().stream().map(addressEntityToDtoConverter::convert).collect(Collectors.toList());
    }

    public AddressDto getAddress(long id) {

        return addressEntityToDtoConverter.convert(addressRepository.findById(id)
                .orElseThrow(() -> new AddressNotFoundException(id)));
    }

    public AddressDto updateAddress(UpdateAddressRequest request, long id) {

        Address address = addressRepository.findById(id).orElseThrow(() -> new AddressNotFoundException());
        Address updatedFields = updateAddressRequestConverter.convert(request);

        address.setCity(updatedFields.getCity());
        address.setContent(request.getContent());
        address.setCounty(updatedFields.getCounty());

        return addressEntityToDtoConverter.convert(addressRepository.save(address));
    }

    public void deleteAddress(long id) {

        addressRepository.deleteById(id);
    }

}
