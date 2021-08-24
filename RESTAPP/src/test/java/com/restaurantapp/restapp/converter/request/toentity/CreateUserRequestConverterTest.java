package com.restaurantapp.restapp.converter.request.toentity;

import com.restaurantapp.restapp.model.converter.create.request.CreateUserRequestConverter;
import com.restaurantapp.restapp.model.entity.User;
import com.restaurantapp.restapp.model.request.create.CreateUserRequest;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

@RunWith(MockitoJUnitRunner.class)
public class CreateUserRequestConverterTest {

    @Spy
    @InjectMocks
    private CreateUserRequestConverter createUserRequestConverter;

    @Test
    public void convert() {

        User userExpected = this.generateUser();
        Mockito.doReturn(userExpected).when(createUserRequestConverter).convert(Mockito.any(CreateUserRequest.class));
        User userActaul = createUserRequestConverter.convert(new CreateUserRequest());
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