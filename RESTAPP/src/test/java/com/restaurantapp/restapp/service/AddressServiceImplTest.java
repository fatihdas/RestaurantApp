package com.restaurantapp.restapp.service;

import com.restaurantapp.restapp.model.converter.create.request.CreateAddressRequestConverter;
import com.restaurantapp.restapp.model.converter.entity.todto.AddressEntityToDtoConverter;
import com.restaurantapp.restapp.model.dto.AddressDto;
import com.restaurantapp.restapp.model.entity.Address;
import com.restaurantapp.restapp.model.request.create.CreateAddressRequest;
import com.restaurantapp.restapp.model.request.update.UpdateAddressRequest;
import com.restaurantapp.restapp.repository.AddressRepository;
import com.restaurantapp.restapp.service.impl.AddressServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AddressServiceImplTest {

    @Mock
    private AddressRepository addressRepository;

    @Mock
    private CreateAddressRequestConverter createAddressRequestConverter;

    @Mock
    private AddressEntityToDtoConverter addressEntityToDtoConverter;

    @Spy
    @InjectMocks
    private AddressServiceImpl addressServiceImpl;

    @Test
    public void save() {

        AddressDto addressDto = this.generateAddress();
        CreateAddressRequest request = CreateAddressRequest.builder().id(123).build();

        Mockito.when(createAddressRequestConverter.convert(Mockito.any(CreateAddressRequest.class)))
                .thenReturn(new Address());
        Mockito.when(addressRepository.save(Mockito.any(Address.class))).thenReturn(new Address());
        Mockito.when(addressEntityToDtoConverter.convert(Mockito.any(Address.class))).thenReturn(addressDto);

        AddressDto createAddress = addressServiceImpl.createAddress(request);

        Assertions.assertEquals(request.getId(), createAddress.getId());
    }

    @Test
    public void getById() {

        AddressDto address = this.generateAddress();

        Mockito.when(addressRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.of(new Address()));
        Mockito.when(addressEntityToDtoConverter.convert(Mockito.any(Address.class))).thenReturn(address);
        AddressDto createAddress = addressServiceImpl.getAddress(2);

        Assertions.assertEquals(address, createAddress);
    }

    @Test
    public void update() {

        AddressDto address = this.generateAddress();
        String message = "Address has been updated! id:" + address.getId();

        Mockito.when(addressRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.of(new Address()));

        String createAddress = addressServiceImpl.updateAddress(new UpdateAddressRequest(), address.getId());

        Assertions.assertEquals(message, createAddress);
    }

    private AddressDto generateAddress() {

        return AddressDto.builder()
                .cityName("İstanbul")
                .countyName("Pendik")
                .content("Güzelyalı mah.")
                .id(123)
                .build();

    }

//    private AddressDto generateAddressDto() {
//
//        return AddressDto.builder()
//                .city(City.builder().name("İstanbul").build())
//                .county(County.builder().name("Pendik").build())
//                .content("Güzelyalı mah.")
//                .user(User.builder().name("test").build())
//                .build();
//
//    }
//
//    private Address generateAddress(CreateAddressRequest request) {
//
//        return Address.builder()
//                .branch(request.getBranch())
//                .user(request.getUser())
//                .content(request.getContent())
//                .county(request.getCounty())
//                .city(request.getCity())
//                .build();
//    }

}