package com.restaurantapp.restapp.converter.request.toentity;

import com.restaurantapp.restapp.model.converter.create.request.UserIdToEntityConverter;
import com.restaurantapp.restapp.model.dto.UserDto;
import com.restaurantapp.restapp.model.entity.User;
import com.restaurantapp.restapp.service.impl.UserServiceImpl;
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
public class UserIdToEntityConverterTest {

    @Spy
    @InjectMocks
    private UserIdToEntityConverter userIdToEntityConverter;

    @Mock
    private UserServiceImpl userService;


    @Test
    public void convert() {

        User userExpected = this.generateUser();
        Mockito.when(userService.getUserById(Mockito.anyLong())).thenReturn(new User());
        User userActaul = userIdToEntityConverter.convert(Mockito.anyLong());
        Assertions.assertEquals(userExpected, userActaul);

    }

    private User generateUser() {

        return User.builder()
                .id(111)
                .roles(new ArrayList<>())
                .email("testmail")
                .name("Reddington")
                .password("eliz")
                .restaurantList(new ArrayList<>())
                .build();
    }
}