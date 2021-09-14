package com.restaurantapp.restapp.controller;

import com.restaurantapp.restapp.model.dto.UserDto;
import com.restaurantapp.restapp.model.request.create.CreateUserRequest;
import com.restaurantapp.restapp.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    private static final String NAME = "Murat";
    private static final String PASSWORD = "1a2ss3ddd";
    private static final String EMAIL = "murat.albayrak@gmail.com";
    private static final String ROLE = "SELLER";
    @Mock
    private UserServiceImpl userService;

    @InjectMocks
    private UserController userController;

    @Test
    public void whenCreateUserCalledByCreateUserRequest_thenReturnSavedUser() {
        UserDto userDto = this.generateUser();
        CreateUserRequest request = CreateUserRequest.builder()
                .name(NAME)
                .password(PASSWORD)
                .email(EMAIL)
                .build();
        Mockito.when(userService.createUser(request)).thenReturn(userDto);
        ResponseEntity<UserDto> responseEntity = userController.createUser(request);
        Assertions.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        Assertions.assertEquals(NAME, responseEntity.getBody().getName());
        Assertions.assertEquals(PASSWORD, responseEntity.getBody().getPassword());
        Assertions.assertEquals(EMAIL, responseEntity.getBody().getEmail());
    }

    @Test
    public void whenChangeUserRoleCalledByUserIdAndRole_thenUserChangingRole() {
        UserDto userDto = this.generateUser();
        Mockito.when(userService.updateUserRole(ROLE, 1L)).thenReturn(userDto);
        ResponseEntity<UserDto> responseEntity = userController.changeUserRole(ROLE, 1L);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

    }

    private UserDto generateUser() {
        return UserDto.builder()
                .name(NAME)
                .password(PASSWORD)
                .email(EMAIL)
                .build();
    }
}