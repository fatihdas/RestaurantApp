package com.restaurantapp.restapp.service.impl;

import com.restaurantapp.restapp.exception.AddressNotFoundException;
import com.restaurantapp.restapp.model.converter.create.request.CreateAddressRequestConverter;
import com.restaurantapp.restapp.model.converter.entity.todto.AddressEntityToDtoConverter;
import com.restaurantapp.restapp.model.dto.AddressDto;
import com.restaurantapp.restapp.model.entity.Address;
import com.restaurantapp.restapp.model.entity.Branch;
import com.restaurantapp.restapp.model.entity.User;
import com.restaurantapp.restapp.model.request.create.CreateAddressRequest;
import com.restaurantapp.restapp.repository.AddressRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class AddressServiceImplTest {
    @Mock
    private AddressRepository addressRepository;

    @Mock
    private AddressEntityToDtoConverter addressEntityToDtoConverter;

    @Mock
    private CreateAddressRequestConverter createAddressRequestConverter;

    @InjectMocks
    private AddressServiceImpl addressService;

    @Test
    public void whenCreateAddressCalledByAddressCreateRequestByUserId_thenReturnSavedUserAddressDto() {
        Address address = Address.builder().user(User.builder().id(1l).build()).content("testAddress").build();
        CreateAddressRequest request = CreateAddressRequest.builder().userId(1l).content("testAddress").build();
        AddressDto addressDtoExpected = AddressDto.builder().userId(1l).content("testAddress").build();
        Mockito.when(createAddressRequestConverter.convert(request)).thenReturn(address);
        Mockito.when(addressRepository.save(address)).thenReturn(address);
        Mockito.when(addressEntityToDtoConverter.convert(address)).thenReturn(addressDtoExpected);

        AddressDto addressDtoActual = addressService.createAddress(request);
        Assertions.assertEquals(addressDtoExpected.getContent(),addressDtoActual.getContent());
        Assertions.assertEquals(addressDtoExpected.getUserId(),addressDtoActual.getUserId());
        Assertions.assertEquals(0,addressDtoActual.getBranchId());
    }

    @Test
    public void whenCreateAddressCalledByAddressCreateRequestByBranchId_thenReturnSavedBranchAddressDto() {
        Address address = Address.builder().branch(Branch.builder().id(1l).build()).content("testAddress").build();
        CreateAddressRequest request = CreateAddressRequest.builder().branchId(1l).content("testAddress").build();
        AddressDto addressDtoExpected = AddressDto.builder().branchId(1l).content("testAddress").build();
        Mockito.when(createAddressRequestConverter.convert(request)).thenReturn(address);
        Mockito.when(addressRepository.save(address)).thenReturn(address);
        Mockito.when(addressEntityToDtoConverter.convert(address)).thenReturn(addressDtoExpected);

        AddressDto addressDtoActual = addressService.createAddress(request);
        Assertions.assertEquals(addressDtoExpected.getContent(),addressDtoActual.getContent());
        Assertions.assertEquals(addressDtoExpected.getUserId(),addressDtoActual.getUserId());
        Assertions.assertEquals(0,addressDtoActual.getUserId());
    }

    @Test
    public void whenGetAddressCalledByValidAddressId_thenReturnAddressDto() {
        Address address = Address.builder().branch(Branch.builder().id(1l).build()).content("testAddress").build();
        AddressDto addressDtoExpected = AddressDto.builder().branchId(1l).content("testAddress").build();
        Mockito.when(addressRepository.findById(1l)).thenReturn(java.util.Optional.ofNullable(address));
        Mockito.when(addressEntityToDtoConverter.convert(address)).thenReturn(addressDtoExpected);

        AddressDto addressDtoActual = addressService.getAddress(1l);
        Assertions.assertEquals(addressDtoExpected.getUserId(),addressDtoActual.getUserId());
//        Assertions.assertEquals(addressDtoExpected.getContent(),addressDtoActual.getContent());
    }

    @Test(expected = AddressNotFoundException.class)
    public void whenGetAddressCalledByNonExistAddressId_thenReturnAddressNotFoundException() {
        Mockito.when(addressRepository.findById(1l)).thenReturn(Optional.empty());
        AddressDto addressDtoActual = addressService.getAddress(1l);
//        Assertions.assertEquals(addressDtoExpected.getContent(),addressDtoActual.getContent());
    }

    @Test
    public void whenGetUserAddressesCalledByValidUserId_thenReturnUserAddressDtoList() {
        Address address = Address.builder().id(1l).user(User.builder().id(1l).build()).build();
        AddressDto addressDto = AddressDto.builder().id(1l).userId(1l).build();
        Mockito.when(addressEntityToDtoConverter.convert(address)).thenReturn(addressDto);
        Mockito.when(addressRepository.findAllByUserId(1l)).thenReturn(Collections.singletonList(address));

        List<AddressDto> addressDtoList = addressService.getUserAdresses(1l);
        Assertions.assertEquals(addressDto.getUserId(),addressDtoList.get(0).getUserId());
    }


}