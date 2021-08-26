package com.restaurantapp.restapp.converter.entity.todto;

import com.restaurantapp.restapp.model.converter.entity.todto.AddressEntityToDtoConverter;
import com.restaurantapp.restapp.model.converter.entity.todto.UserEntityToDtoConverter;
import com.restaurantapp.restapp.model.dto.AddressDto;
import com.restaurantapp.restapp.model.dto.UserDto;
import com.restaurantapp.restapp.model.entity.Address;
import com.restaurantapp.restapp.model.entity.User;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

@RunWith(MockitoJUnitRunner.class)
public class UserEntityToDtoConverterTest {

    @Mock
    private AddressEntityToDtoConverter addressEntityToDtoConverter;

    @Spy
    @InjectMocks
    private UserEntityToDtoConverter userEntityToDtoConverter;

    @Test
    public void convert() {

        UserDto userExpected = this.generateUser();
        Mockito.when(addressEntityToDtoConverter.convert(Mockito.any(Address.class))).thenReturn(new AddressDto());
        Mockito.doReturn(userExpected).when(userEntityToDtoConverter).convert(Mockito.any(User.class));
        UserDto userActual = userEntityToDtoConverter.convert(new User());

        Assertions.assertEquals(userExpected,userActual);


    }

    private UserDto generateUser() {

        return UserDto.builder()
                .id(111)
                .rolesList(new ArrayList<>())
                .email("testmail")
                .name("Reddington")
                .password("eliz")
                .addressDtoList(new ArrayList<>())
                .build();
    }
}