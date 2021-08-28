package com.restaurantapp.restapp.service;

import com.restaurantapp.restapp.exception.AddressNotFoundException;
import com.restaurantapp.restapp.model.converter.create.request.CreateAddressRequestConverter;
import com.restaurantapp.restapp.model.converter.entity.todto.AddressEntityToDtoConverter;
import com.restaurantapp.restapp.model.dto.AddressDto;
import com.restaurantapp.restapp.model.dto.UserDto;
import com.restaurantapp.restapp.model.entity.Address;
import com.restaurantapp.restapp.model.request.create.CreateAddressRequest;
import com.restaurantapp.restapp.repository.AddressRepository;
import com.restaurantapp.restapp.service.impl.AddressServiceImpl;
import com.restaurantapp.restapp.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class AddressServiceImplTest {

    private static final String CITY_NAME = "İstanbul";
    private static final String COUNTY_NAME = "Pendik";
    private static final String CONTENT = "Güzelyalı mah.";
    private static final int ID = 123;
    private static final int USER_ID = 1;

    @Mock
    private UserServiceImpl userService;

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

        CreateAddressRequest request = this.generateCreateAddressRequest();
        AddressDto addressDto = this.generateAddressDto();
        Mockito.when(createAddressRequestConverter.convert(Mockito.any(CreateAddressRequest.class)))
                .thenReturn(new Address());
        Mockito.when(addressRepository.save(Mockito.any(Address.class))).thenReturn(new Address());
        Mockito.when(addressEntityToDtoConverter.convert(Mockito.any(Address.class))).thenReturn(addressDto);
        AddressDto addressActual = addressServiceImpl.createAddress(request);

        Assertions.assertEquals(request.getId(), addressActual.getId());
        Assertions.assertEquals(request.getContent(), addressActual.getContent());
        Assertions.assertEquals(request.getCityName(), addressActual.getCityName());
        Assertions.assertEquals(request.getCountyName(), addressActual.getCountyName());
    }

    @Test
    public void whenGetByIdCalledWithValidAddressId_itShouldReturnValidAddressDto() {

        AddressDto address = this.generateAddressDto();

        Mockito.when(addressRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.of(new Address()));
        Mockito.when(addressEntityToDtoConverter.convert(Mockito.any(Address.class))).thenReturn(address);
        AddressDto createAddress = addressServiceImpl.getAddress(2);

        Assertions.assertEquals(address, createAddress);
    }

    @Test(expected = AddressNotFoundException.class)
    public void whenGetByIdCalledWithNonValidAddressId_itShouldThrowAddressNotFoundException() {

        Mockito.when(addressRepository.findById(11l)).thenReturn(Optional.empty());
        addressServiceImpl.getAddress(2);

    }

    @Test
    public void whenGetUserAddressesCalledWithValidUserId_itShouldReturnListofAddressDto() {

        UserDto userDto = UserDto.builder().id(ID).addressDtoList(Arrays.asList(this.generateAddressDto())).build();
        Mockito.when(userService.getUser(ID)).thenReturn(userDto);
        List<AddressDto> addressDtoList = addressServiceImpl.getUserAdresses(ID);

        Assertions.assertEquals(ID,addressDtoList.get(0).getId());
        Assertions.assertEquals(CITY_NAME,addressDtoList.get(0).getCityName());
        Assertions.assertEquals(COUNTY_NAME,addressDtoList.get(0).getCountyName());
        Assertions.assertEquals(CONTENT,addressDtoList.get(0).getContent());

    }

//    @Test
//    public void update() {
//
//        AddressDto address = this.generateAddress();
//        String message = "Address has been updated! id:" + address.getId();
//
//        Mockito.when(addressRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.of(new Address()));
//
//        String createAddress = addressServiceImpl.updateAddress(new UpdateAddressRequest(), address.getId());
//
//        Assertions.assertEquals(message, createAddress);
//    }

    private CreateAddressRequest generateCreateAddressRequest() {

        return CreateAddressRequest.builder()
                .cityName(CITY_NAME)
                .countyName(COUNTY_NAME)
                .content(CONTENT)
                .id(ID)
                .build();

    }

    private AddressDto generateAddressDto() {

        return AddressDto.builder()
                .cityName(CITY_NAME)
                .countyName(COUNTY_NAME)
                .content(CONTENT)
                .id(ID)
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