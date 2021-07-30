package com.restaurantapp.restapp.service;

import com.restaurantapp.restapp.model.Address;
import com.restaurantapp.restapp.model.City;
import com.restaurantapp.restapp.model.County;
import com.restaurantapp.restapp.model.User;
import com.restaurantapp.restapp.repository.AddressRepository;
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
public class AddressServiceTest {

    @Mock
    private AddressRepository addressRepository;

    @InjectMocks
    private AddressService addressService;

    @Test
    public void save() {

        Address address = this.generateAddress();

        Mockito.when(addressRepository.save(Mockito.any(Address.class))).thenReturn(address);

        Address createAddress = addressService.save(new Address());

        Assertions.assertEquals(address, createAddress);
    }

    @Test
    public void getAll() {

        List<Address> addressList = new ArrayList<>();
        addressList.add(this.generateAddress());

        Mockito.when(addressRepository.findAll()).thenReturn(addressList);

        List<Address> createAddressList = addressService.getAll();

        Assertions.assertEquals(addressList, createAddressList);
    }

    @Test
    public void getById() {

        Address address = this.generateAddress();

        Mockito.when(addressRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(address));

        Address createAddress = addressService.getById(2);

        Assertions.assertEquals(address, createAddress);
    }

    @Test
    public void update() {

        Address address = this.generateAddress();

        Mockito.when(addressRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(address));

        Mockito.when(addressRepository.save(Mockito.any(Address.class))).thenReturn(address);

        Address createAddress = addressService.update(new Address());

        Assertions.assertEquals(address, createAddress);
    }

    @Test
    public void delete() {

        addressRepository.deleteById(2L);
        Mockito.verify(addressRepository).deleteById(2L);

        String deleteAddress = addressService.delete(3);

        Assertions.assertEquals("SUCCESS", deleteAddress);
    }

    private Address generateAddress() {

        return Address.builder()
                .city(City.builder().name("İstanbul").build())
                .county(County.builder().name("Pendik").build())
                .content("Güzelyalı mah.")
                .user(User.builder().name("test").build())
                .build();

    }
}