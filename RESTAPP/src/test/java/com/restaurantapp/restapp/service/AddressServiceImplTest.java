package com.restaurantapp.restapp.service;

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
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class AddressServiceImplTest {

    @Mock
    private AddressRepository addressRepository;

    @InjectMocks
    private AddressServiceImpl addressServiceImpl;

    @Test
    public void save() {

        Address address = this.generateAddress();

        Mockito.when(addressRepository.save(Mockito.any(Address.class))).thenReturn(address);

        AddressDto createAddress = addressServiceImpl.createAddress(new CreateAddressRequest());

        Assertions.assertEquals(address, createAddress);
    }

    @Test
    public void getAll() {

        List<Address> addressList = new ArrayList<>();
        addressList.add(this.generateAddress());

        Mockito.when(addressRepository.findAll()).thenReturn(addressList);

        List<AddressDto> createAddressList = addressServiceImpl.getAllAddresses();

        Assertions.assertEquals(addressList, createAddressList);
    }

    @Test
    public void getById() {

        Address address = this.generateAddress();

        Mockito.when(addressRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(address));

        AddressDto createAddress = addressServiceImpl.getAddress(2);

        Assertions.assertEquals(address, createAddress);
    }

    @Test
    public void update() {

        Address address = this.generateAddress();

        Mockito.when(addressRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(address));

        Mockito.when(addressRepository.save(Mockito.any(Address.class))).thenReturn(address);

        AddressDto createAddress = addressServiceImpl.updateAddress(new UpdateAddressRequest(), 2);

        Assertions.assertEquals(address, createAddress);
    }

    @Test
    public void delete() {

        addressServiceImpl.deleteAddress(Mockito.anyLong());
        Mockito.verify(addressRepository).deleteById(Mockito.anyLong());

    }

    private Address generateAddress() {

        return Address.builder()
                .cityName("İstanbul")
                .countyName("Pendik")
                .content("Güzelyalı mah.")
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