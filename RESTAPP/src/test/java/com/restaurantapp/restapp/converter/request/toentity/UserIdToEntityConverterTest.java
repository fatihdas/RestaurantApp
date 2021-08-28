package com.restaurantapp.restapp.converter.request.toentity;

import com.restaurantapp.restapp.model.converter.create.request.UserIdToEntityConverter;
import com.restaurantapp.restapp.model.dto.UserDto;
import com.restaurantapp.restapp.model.entity.User;
import com.restaurantapp.restapp.model.entity.enumerated.Roles;
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


    private static final int ID = 111;
    private static final String EMAIL = "testmail";
    private static final String NAME = "Reddington";
    private static final String PASSWORD = "eliz";
    @Mock
    private UserServiceImpl userService;

    @Spy
    @InjectMocks
    private UserIdToEntityConverter userIdToEntityConverter;

    @Test
    public void convert() {

        User userExpected = this.generateUser();
        Mockito.when(userService.getUserById(Mockito.anyLong())).thenReturn(userExpected);
        User userActaul = userIdToEntityConverter.convert(111);

        Assertions.assertEquals(ID, userActaul.getId());
        Assertions.assertEquals(NAME, userActaul.getName());
        Assertions.assertEquals(PASSWORD, userActaul.getPassword());
        Assertions.assertEquals(EMAIL, userActaul.getEmail());

    }

    private User generateUser() {

        return User.builder()
                .id(ID)
                .roles(new ArrayList<>())
                .email(EMAIL)
                .name(NAME)
                .password(PASSWORD)
                .restaurantList(new ArrayList<>())
                .build();
    }
}