package com.restaurantapp.restapp.converter.entity.todto;

import com.restaurantapp.restapp.model.converter.entity.todto.AddressEntityToDtoConverter;
import com.restaurantapp.restapp.model.converter.entity.todto.UserEntityToDtoConverter;
import com.restaurantapp.restapp.model.dto.UserDto;
import com.restaurantapp.restapp.model.entity.User;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

@RunWith(MockitoJUnitRunner.class)
public class UserEntityToDtoConverterTest {

    private static final int ID = 111;
    private static final String EMAIL = "testmail";
    private static final String NAME = "Reddington";
    private static final String PASSWORD = "eliz";

    @Mock
    private AddressEntityToDtoConverter addressEntityToDtoConverter;

    @InjectMocks
    private UserEntityToDtoConverter userEntityToDtoConverter;

    @Test
    public void convert() {

        User user = this.generateUser();
        UserDto userActual = userEntityToDtoConverter.convert(user);

        Assertions.assertEquals(ID, userActual.getId());
        Assertions.assertEquals(NAME, userActual.getName());
        Assertions.assertEquals(PASSWORD, userActual.getPassword());
        Assertions.assertEquals(EMAIL, userActual.getEmail());


    }

    private User generateUser() {

        return User.builder()
                .id(ID)
                .roles(new ArrayList<>())
                .email(EMAIL)
                .name(NAME)
                .password(PASSWORD)
                .addressList(new ArrayList<>())
                .build();
    }
}