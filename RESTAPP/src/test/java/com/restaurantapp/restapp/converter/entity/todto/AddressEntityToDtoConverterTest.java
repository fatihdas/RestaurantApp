package com.restaurantapp.restapp.converter.entity.todto;

import com.restaurantapp.restapp.model.converter.entity.todto.AddressEntityToDtoConverter;
import com.restaurantapp.restapp.model.dto.AddressDto;
import com.restaurantapp.restapp.model.entity.Address;
import com.restaurantapp.restapp.model.entity.Branch;
import com.restaurantapp.restapp.model.entity.County;
import com.restaurantapp.restapp.model.entity.User;
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
    private static final long USER_ID = 1L;
    private static final long BRANCH_ID = 2L;
    private static final long COUNTY_ID = 736L;

    @Spy
    private AddressEntityToDtoConverter addressEntityToDtoConverter;

    @Test
    public void whenConvertCalledByUserId_thenReturnAddressDto() {

        Address address = this.generateUserAddress();

        AddressDto addressActual = addressEntityToDtoConverter.convert(address);
        Assertions.assertEquals(ID, addressActual.getId());
        Assertions.assertEquals(COUNTY_NAME, addressActual.getCountyName());
        Assertions.assertEquals(COUNTY_ID, addressActual.getCountyId());
    }

    @Test
    public void whenConvertCalledByBranchId_thenReturnAddressDto() {
        Address address = this.generateBranchAddress();

        AddressDto addressActual = addressEntityToDtoConverter.convert(address);
        Assertions.assertEquals(ID, addressActual.getId());
        Assertions.assertEquals(COUNTY_NAME, addressActual.getCountyName());
    }

    private Address generateUserAddress() {

        return Address.builder()
                .id(ID)
                .content(CONTENT)
                .user(User.builder()
                        .id(USER_ID)
                        .build())
                .county(County.builder()
                        .id(COUNTY_ID)
                        .name(COUNTY_NAME)
                        .build())
                .branch(null)
                .build();
    }

    private Address generateBranchAddress() {

        return Address.builder()
                .id(ID)
                .user(null)
                .branch(Branch.builder()
                        .id(BRANCH_ID)
                        .build())
                .content(CONTENT)
                .county(County.builder()
                        .name(COUNTY_NAME)
                        .id(COUNTY_ID)
                        .build())
                .build();
    }
}