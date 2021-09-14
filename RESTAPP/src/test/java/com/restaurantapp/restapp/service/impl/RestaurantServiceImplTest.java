package com.restaurantapp.restapp.service.impl;

import com.restaurantapp.restapp.exception.InvalidRoleException;
import com.restaurantapp.restapp.exception.RestaurantNotFoundException;
import com.restaurantapp.restapp.model.converter.create.request.CreateRestaurantRequestConverter;
import com.restaurantapp.restapp.model.converter.entity.todto.RestaurantEntityToDtoConverter;
import com.restaurantapp.restapp.model.dto.RestaurantDto;
import com.restaurantapp.restapp.model.entity.Restaurant;
import com.restaurantapp.restapp.model.entity.enumerated.UserRoles;
import com.restaurantapp.restapp.model.request.create.CreateRestaurantRequest;
import com.restaurantapp.restapp.model.request.update.UpdateRestaurantRequest;
import com.restaurantapp.restapp.repository.RestaurantRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class RestaurantServiceImplTest {

    private static final long ID = 1L;
    private static final String NAME = "Nusr-et";
    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private RestaurantEntityToDtoConverter restaurantEntityToDtoConverter;

    @Mock
    private CreateRestaurantRequestConverter createRestaurantRequestConverter;

    @Mock
    private UserServiceImpl userService;

    @InjectMocks
    private RestaurantServiceImpl restaurantService;

    @Test
    public void whenCreateRestaurantCalled_thenReturnSavedRestaurantDto() throws Exception {
        CreateRestaurantRequest request = CreateRestaurantRequest.builder()
                .id(ID)
                .name(NAME)
                .build();
        Restaurant restaurant = Restaurant.builder()
                .id(ID)
                .name(NAME)
                .build();
        RestaurantDto restaurantDto = RestaurantDto.builder()
                .id(ID)
                .name(NAME)
                .build();
        Mockito.when(userService.hasRole(UserRoles.SELLER)).thenReturn(true);
        Mockito.when(createRestaurantRequestConverter.convert(request)).thenReturn(restaurant);
        Mockito.when(restaurantRepository.save(restaurant)).thenReturn(restaurant);
        Mockito.when(restaurantEntityToDtoConverter.convert(restaurant)).thenReturn(restaurantDto);

        RestaurantDto result = restaurantService.createRestaurant(request);
        Assertions.assertEquals(ID, result.getId());
        Assertions.assertEquals(NAME, result.getName());
    }

    @Test(expected = InvalidRoleException.class)
    public void whenCreateRestaurantCalled_hasNotUserRolesSeller_thenThrowInvalidRoleException(){
        Mockito.when(userService.hasRole(UserRoles.SELLER)).thenReturn(false);
        restaurantService.createRestaurant(new CreateRestaurantRequest());
    }

    @Test
    public void whenGetAllRestaurantsCalled_thenReturnRestaurantDtoList() {
        List<Restaurant> restaurantList = Collections.singletonList(Restaurant.builder()
                .name(NAME)
                .id(ID)
                .build());
        RestaurantDto restaurantDto = RestaurantDto.builder()
                .name(NAME)
                .id(ID)
                .build();
        Mockito.when(restaurantRepository.findAll()).thenReturn(restaurantList);
        Mockito.when(restaurantEntityToDtoConverter.convert(Mockito.any(Restaurant.class))).thenReturn(restaurantDto);

        List<RestaurantDto> result = restaurantService.getAllRestaurants();
        Assertions.assertEquals(ID, result.get(0).getId());
        Assertions.assertEquals(NAME, result.get(0).getName());
    }

    @Test
    public void whenGetRestaurantDtoCalledByValidRestaurantId_thenReturnRestaurantDto() {
        Restaurant restaurant = Restaurant.builder()
                .id(ID)
                .name(NAME)
                .build();
        RestaurantDto restaurantDto = RestaurantDto.builder()
                .id(ID)
                .name(NAME)
                .build();
        Mockito.when(restaurantRepository.findById(ID)).thenReturn(java.util.Optional.ofNullable(restaurant));
        Mockito.when(restaurantEntityToDtoConverter.convert(restaurant)).thenReturn(restaurantDto);

        RestaurantDto result = restaurantService.getRestaurantDto(ID);
        Assertions.assertEquals(ID, result.getId());
        Assertions.assertEquals(NAME, result.getName());
    }

    @Test(expected = RestaurantNotFoundException.class)
    public void whenGetRestaurantDtoCalledByInValidRestaurantId_thenThrowRestaurantNotFoundException() {
        Mockito.when(restaurantRepository.findById(ID)).thenReturn(Optional.empty());
        restaurantService.getRestaurantDto(ID);
    }

    @Test
    public void whenGetRestaurantCalledByValidRestaurantId_thenReturnRestaurant() {
        Restaurant restaurant = Restaurant.builder()
                .name(NAME)
                .id(ID)
                .build();
        Mockito.when(restaurantRepository.findById(ID)).thenReturn(Optional.ofNullable(restaurant));
        Restaurant result = restaurantService.getRestaurant(ID);
        Assertions.assertEquals(ID, result.getId());
        Assertions.assertEquals(NAME, result.getName());
    }

    @Test(expected = RestaurantNotFoundException.class)
    public void whenGetRestaurantCalledByInValidRestaurantId_thenThrowRestaurantNotFoundException() {
        Mockito.when(restaurantRepository.findById(ID)).thenReturn(Optional.empty());
        restaurantService.getRestaurant(ID);
    }

    @Test
    public void whenUpdateRestaurantCalledByValidRestaurantId_UpdateRestaurantRequest_thenReturnSuccessMessage() {
        UpdateRestaurantRequest request = UpdateRestaurantRequest.builder()
                .name("changed name")
                .build();
        Restaurant restaurant = Restaurant.builder()
                .id(ID)
                .name(NAME)
                .build();
        Mockito.when(restaurantRepository.findById(ID)).thenReturn(Optional.ofNullable(restaurant));
        String expectedMessage = "Restaurant has been updated! id:" + ID;
        String result = restaurantService.updateRestaurant(request, ID);
        Assertions.assertEquals(expectedMessage, result);

    }

    @Test(expected = RestaurantNotFoundException.class)
    public void whenUpdateRestaurantCalledByInValidRestaurantId_thenThrowRestaurantNotFoundException() {
        Mockito.when(restaurantRepository.findById(ID)).thenReturn(Optional.empty());
        restaurantService.updateRestaurant(new UpdateRestaurantRequest(), ID);
    }

    @Test
    public void deleteRestaurant() {
        restaurantService.deleteRestaurant(ID);
        Mockito.verify(restaurantRepository).deleteById(ID);
    }
}