package com.restaurantapp.restapp.converter.entity.todto;

import com.restaurantapp.restapp.model.converter.entity.todto.UserEntityToDtoConverter;
import com.restaurantapp.restapp.model.dto.UserDto;
import com.restaurantapp.restapp.model.entity.User;
import com.restaurantapp.restapp.model.entity.enumerated.UserRoles;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;

@RunWith(MockitoJUnitRunner.class)
public class UserEntityToDtoConverterTest {

    private static final int ID = 111;
    private static final String EMAIL = "testmail";
    private static final String NAME = "Reddington";
    private static final String PASSWORD = "eliz";

    @Spy
    private UserEntityToDtoConverter userEntityToDtoConverter;

    @Test
    public void whenConvertCalledByUser_thenReturnUserDto() {

        User user = this.generateUser();
        UserDto userActual = userEntityToDtoConverter.convert(user);

        Assertions.assertEquals(ID, userActual.getId());
        Assertions.assertEquals(NAME, userActual.getName());
        Assertions.assertEquals(PASSWORD, userActual.getPassword());
        Assertions.assertEquals(EMAIL, userActual.getEmail());
        Assertions.assertEquals(UserRoles.BUYER, userActual.getUserRolesList().get(0));
        Assertions.assertEquals(null,user.getAddressList());
        Assertions.assertEquals(null,user.getCommentList());
        Assertions.assertEquals(null,user.getRestaurantList());

    }

    private User generateUser() {

        return User.builder()
                .id(ID)
                .roles(Arrays.asList(UserRoles.BUYER))
                .email(EMAIL)
                .name(NAME)
                .password(PASSWORD)
                .addressList(null)
                .restaurantList(null)
                .commentList(null)
                .build();
    }
}