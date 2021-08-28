package com.restaurantapp.restapp.converter.entity.todto;

import com.restaurantapp.restapp.model.converter.entity.todto.AddressEntityToDtoConverter;
import com.restaurantapp.restapp.model.dto.AddressDto;
import com.restaurantapp.restapp.model.entity.Address;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AddressEntityToDtoConverterTest {

    private static final int ID = 2;
    private static final String COUNTY_NAME = "kadıköy";
    private static final String CITY_NAME = "İstanbul";
    private static final String CONTENT = "moda";

    @Spy
    private AddressEntityToDtoConverter addressEntityToDtoConverter;

    @Test
    public void convert() {

        Address address = this.generateAddress();

        AddressDto addressActual = addressEntityToDtoConverter.convert(address);
        Assertions.assertEquals(ID, addressActual.getId());
        Assertions.assertEquals(COUNTY_NAME, addressActual.getCountyName());
        Assertions.assertEquals(CITY_NAME, addressActual.getCityName());
    }

    private Address generateAddress() {

        return Address.builder()
                .id(ID)
                .countyName(COUNTY_NAME)
                .cityName(CITY_NAME)
                .content(CONTENT)
                .build();
    }
}