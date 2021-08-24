package com.restaurantapp.restapp.service;

import com.restaurantapp.restapp.model.dto.RestaurantDto;
import com.restaurantapp.restapp.model.entity.Restaurant;
import com.restaurantapp.restapp.model.entity.User;
import com.restaurantapp.restapp.model.request.create.CreateRestaurantRequest;
import com.restaurantapp.restapp.model.request.update.UpdateRestaurantRequest;
import com.restaurantapp.restapp.repository.RestaurantRepository;
import com.restaurantapp.restapp.service.impl.RestaurantServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class RestaurantServiceImplTest {

    @Mock
    private RestaurantRepository restaurantRepository;

    @InjectMocks
    private RestaurantServiceImpl restaurantServiceImpl;

    @Test
    public void save() {

        Restaurant restaurant = this.generateRestaurant();

        Mockito.when(restaurantRepository.save(Mockito.any(Restaurant.class))).thenReturn(restaurant);

        RestaurantDto createRestaurant = restaurantServiceImpl.createRestaurant(new CreateRestaurantRequest());

        Assertions.assertEquals(restaurant, createRestaurant);
    }

    @Test
    public void getAll() {

        List<Restaurant> restaurantList = new ArrayList<>();
        restaurantList.add(this.generateRestaurant());

        Mockito.when(restaurantRepository.findAll()).thenReturn(restaurantList);

        List<RestaurantDto> createRestaurantList = restaurantServiceImpl.getAllRestaurants();

        Assertions.assertEquals(restaurantList, createRestaurantList);
    }

    @Test
    public void getById() {
        Restaurant restaurant = this.generateRestaurant();

        Mockito.when(restaurantRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(restaurant));

        RestaurantDto createRestaurant = restaurantServiceImpl.getRestaurant(2);

        Assertions.assertEquals(restaurant, createRestaurant);
    }

    @Test
    public void update() {

        Restaurant restaurant = this.generateRestaurant();

        Mockito.when(restaurantRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(restaurant));

        Mockito.when(restaurantRepository.save(Mockito.any(Restaurant.class))).thenReturn(restaurant);

        String createRestaurant = restaurantServiceImpl.updateRestaurant(new UpdateRestaurantRequest(),5);

        Assertions.assertEquals(restaurant, createRestaurant);
    }

    @Test
    public void delete() {

        restaurantRepository.deleteById(2L);
        Mockito.verify(restaurantServiceImpl).deleteRestaurant(2L);
    }

    private Restaurant generateRestaurant() {
        return Restaurant.builder()
                .name("Hatay Medeniyetler Sofrası")
                .user(User.builder().name("CZN Burak").build())
                .build();
    }

}