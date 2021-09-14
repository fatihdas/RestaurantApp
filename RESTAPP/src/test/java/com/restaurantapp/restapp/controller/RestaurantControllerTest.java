package com.restaurantapp.restapp.controller;

import com.restaurantapp.restapp.model.dto.RestaurantDto;
import com.restaurantapp.restapp.model.request.create.CreateRestaurantRequest;
import com.restaurantapp.restapp.service.impl.RestaurantServiceImpl;
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
public class RestaurantControllerTest {

    private static final String NAME = "Nusr-et";
    private static final long USER_ID = 11L;
    @Mock
    private RestaurantServiceImpl restaurantService;

    @InjectMocks
    private RestaurantController restaurantController;

    @Test
    public void whenCreateRestaurantCalledByCreateRestaurantRequest_thenReturnSavedRestaurant() throws Exception {
        RestaurantDto restaurantDto = this.generateRestaurant();
        CreateRestaurantRequest request = CreateRestaurantRequest.builder()
                .name(NAME)
                .userId(USER_ID)
                .build();
        Mockito.when(restaurantService.createRestaurant(request)).thenReturn(restaurantDto);
        ResponseEntity<RestaurantDto> responseEntity = restaurantController.createRestaurant(request);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertEquals(NAME, responseEntity.getBody().getName());
        Assertions.assertEquals(USER_ID, responseEntity.getBody().getUserId());
    }

    private RestaurantDto generateRestaurant() {
        return RestaurantDto.builder()
                .name(NAME)
                .userId(USER_ID)
                .build();
    }
}