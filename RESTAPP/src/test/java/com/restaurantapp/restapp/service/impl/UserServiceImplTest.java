package com.restaurantapp.restapp.service.impl;

import com.restaurantapp.restapp.exception.UserNotFoundException;
import com.restaurantapp.restapp.model.converter.create.request.CreateUserRequestConverter;
import com.restaurantapp.restapp.model.converter.entity.todto.UserEntityToDtoConverter;
import com.restaurantapp.restapp.model.dto.UserDto;
import com.restaurantapp.restapp.model.entity.User;
import com.restaurantapp.restapp.model.entity.enumerated.RolesEnumConverter;
import com.restaurantapp.restapp.model.entity.enumerated.UserRoles;
import com.restaurantapp.restapp.model.request.create.CreateUserRequest;
import com.restaurantapp.restapp.model.request.update.UpdateUserRequest;
import com.restaurantapp.restapp.repository.UserRepository;
import com.restaurantapp.restapp.security.JwtUtil;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    private static final String NAME = "Hugh";
    private static final String EMAIL = "hughjackman@gmail.com";
    private static final String PASSWORD = "password";
    private static final String ROLE = "Seller";
    private static final long ID = 1l;
    private static final String CHANGED_EMAIL = "changed email";
    private static final String CHANGED_PASS = "changed pass";
    private static final String SECRET_KEY = "secret";

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserEntityToDtoConverter userEntityToDtoConverter;

    @Mock
    private CreateUserRequestConverter createUserRequestConverter;

    @Mock
    private RolesEnumConverter rolesEnumConverter;

    @Mock
    private HttpServletRequest httpServletRequest;

    @Mock
    private JwtUtil jwtUtil;

    private UserDetails userDetails;

    @InjectMocks
    private UserServiceImpl userService;


    @Test
    public void whenLoadUserByUsernameCalledByUserName_thenReturnUserDetails() {
        User user = User.builder()
                .name(NAME)
                .password(PASSWORD)
                .email(EMAIL)
                .roles(Collections.singletonList(UserRoles.BUYER))
                .build();
        Mockito.when(userRepository.findByName(NAME)).thenReturn(user);
        UserDetails result = userService.loadUserByUsername(NAME);
        Assertions.assertEquals(NAME, result.getUsername());
        Assertions.assertEquals(PASSWORD, result.getPassword());
    }

    @Test
    public void whenCreateUserCalledByCreateUserRequest_thenReturnSavedUserDto() {
        CreateUserRequest request = CreateUserRequest.builder()
                .email(EMAIL)
                .name(NAME)
                .password(PASSWORD)
                .build();
        User user = User.builder()
                .name(NAME)
                .password(PASSWORD)
                .email(EMAIL)
                .build();
        UserDto userDto = UserDto.builder()
                .email(EMAIL)
                .name(NAME)
                .password(PASSWORD)
                .build();
        Mockito.when(createUserRequestConverter.convert(request)).thenReturn(user);
        Mockito.when(userRepository.save(user)).thenReturn(user);
        Mockito.when(userEntityToDtoConverter.convert(user)).thenReturn(userDto);

        UserDto result = userService.createUser(request);
        Assertions.assertEquals(NAME, result.getName());
        Assertions.assertEquals(PASSWORD, result.getPassword());
        Assertions.assertEquals(EMAIL, result.getEmail());
    }

    @Test
    public void whenUpdateUserRoleCalledByValidUserIdAndRole_thenReturnUpdatedUserDto() {
        User user = User.builder()
                .email(EMAIL)
                .password(PASSWORD)
                .name(NAME)
                .roles(Collections.singletonList(UserRoles.BUYER))
                .build();
        UserDto userDto = UserDto.builder()
                .password(PASSWORD)
                .name(NAME)
                .email(EMAIL)
                .build();
        Mockito.when(rolesEnumConverter.convertToDatabaseColumn(ROLE)).thenReturn(UserRoles.SELLER);
        Mockito.when(userRepository.save(user)).thenReturn(user);
        Mockito.when(userRepository.findById(ID)).thenReturn(java.util.Optional.of(user));
        Mockito.when(userEntityToDtoConverter.convert(user)).thenReturn(userDto);

        UserDto result = userService.updateUserRole(ROLE, ID);
        Assertions.assertEquals(true, user.getRoles().contains(UserRoles.SELLER));
        Assertions.assertEquals(EMAIL, result.getEmail());
    }

    @Test(expected = UserNotFoundException.class)
    public void whenUpdateUserRoleCalledByInValidUserId_thenThrowUserNotFoundException() {
        Mockito.when(userRepository.findById(ID)).thenReturn(Optional.empty());
        userService.updateUserRole(ROLE, ID);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenUpdateUserRoleCalledByValidUserIdAndExistRole_thenThrowIllegalArgumentException() {
        User user = User.builder()
                .email(EMAIL)
                .password(PASSWORD)
                .name(NAME)
                .roles(Collections.singletonList(UserRoles.SELLER))
                .build();
        Mockito.when(userRepository.findById(ID)).thenReturn(Optional.ofNullable(user));
        Mockito.when(rolesEnumConverter.convertToDatabaseColumn(ROLE)).thenReturn(UserRoles.SELLER);
        userService.updateUserRole(ROLE, ID);
    }

    @Test
    public void whenGetAllUsersCalled_thenReturnUserDtoList() {
        List<User> userList = Collections.singletonList(User.builder()
                .name(NAME)
                .password(PASSWORD)
                .email(EMAIL)
                .build());
        UserDto userDto = UserDto.builder()
                .email(EMAIL)
                .name(NAME)
                .password(PASSWORD)
                .build();
        Mockito.when(userRepository.findAll()).thenReturn(userList);
        Mockito.when(userEntityToDtoConverter.convert(Mockito.any(User.class))).thenReturn(userDto);

        List<UserDto> result = userService.getAllUsers();
        Assertions.assertEquals(NAME, result.get(0).getName());
        Assertions.assertEquals(PASSWORD, result.get(0).getPassword());
        Assertions.assertEquals(EMAIL, result.get(0).getEmail());
    }

    @Test
    public void whenGetUserCalledByValidUserId_thenReturnUserDto() {
        User user = User.builder()
                .email(EMAIL)
                .password(PASSWORD)
                .name(NAME)
                .build();
        UserDto userDto = UserDto.builder()
                .password(PASSWORD)
                .name(NAME)
                .email(EMAIL)
                .build();
        Mockito.when(userRepository.findById(ID)).thenReturn(Optional.ofNullable(user));
        Mockito.when(userEntityToDtoConverter.convert(user)).thenReturn(userDto);

        UserDto result = userService.getUser(ID);
        Assertions.assertEquals(NAME, result.getName());
        Assertions.assertEquals(PASSWORD, result.getPassword());
        Assertions.assertEquals(EMAIL, result.getEmail());
    }

    @Test(expected = UserNotFoundException.class)
    public void whenGetUserCalledByInValidUserId_thenThrowUserNotFoundException() {
        Mockito.when(userRepository.findById(ID)).thenReturn(Optional.empty());
        userService.getUser(ID);
    }

    @Test
    public void whenGetUserDtoByNameCalledByUserName_thenReturnUserDto() {
        User user = User.builder()
                .name(NAME)
                .password(PASSWORD)
                .email(EMAIL)
                .build();
        UserDto userDto = UserDto.builder()
                .name(NAME)
                .email(EMAIL)
                .password(PASSWORD)
                .build();
        Mockito.when(userRepository.findByName(NAME)).thenReturn(user);
        Mockito.when(userEntityToDtoConverter.convert(user)).thenReturn(userDto);

        UserDto result = userService.getUserDtoByName(NAME);
        Assertions.assertEquals(NAME, result.getName());
        Assertions.assertEquals(EMAIL, result.getEmail());
    }

    @Test
    public void whenGetUserByNameCalledByUserName_thenReturnUser() {
        User user = User.builder()
                .name(NAME)
                .password(PASSWORD)
                .email(EMAIL)
                .build();
        Mockito.when(userRepository.findByName(NAME)).thenReturn(user);
        User result = userService.getUserByName(NAME);
        Assertions.assertEquals(NAME, result.getName());
    }

    @Test
    public void whenUpdateUserCalledByValidUserIdAndUpdateUserRequest_thenReturnSuccessMessage() {
        UpdateUserRequest request = UpdateUserRequest.builder()
                .email(CHANGED_EMAIL)
                .password(CHANGED_PASS)
                .build();
        User user = User.builder()
                .email(EMAIL)
                .password(PASSWORD)
                .name(NAME)
                .build();
        Mockito.when(userRepository.findById(ID)).thenReturn(Optional.ofNullable(user));
        String expectedMessage = "User has been updated! id:" + ID;
        String result = userService.updateUser(request, ID);
        Assertions.assertEquals(expectedMessage, result);
        Assertions.assertEquals(CHANGED_EMAIL, user.getEmail());
    }

    @Test(expected = UserNotFoundException.class)
    public void whenUpdateUserCalledByIInValidUserId_thenThrowUserNotFoundException() {
        Mockito.when(userRepository.findById(ID)).thenReturn(Optional.empty());
        userService.updateUser(new UpdateUserRequest(), ID);
    }

    @Test
    public void whenDeleteUserCalledByUserId_thenDeleteUser() {
        userService.deleteUser(ID);
        Mockito.verify(userRepository).deleteById(ID);
    }

    @Test
    public void whenHasRoleCalledByUserRoles_() {
    }

    @Test
    public void isOwner() {
    }
}