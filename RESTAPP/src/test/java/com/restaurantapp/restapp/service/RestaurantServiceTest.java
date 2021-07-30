package com.restaurantapp.restapp.service;

import com.restaurantapp.restapp.model.Restaurant;
import com.restaurantapp.restapp.model.User;
import com.restaurantapp.restapp.repository.RestaurantRepository;
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
public class RestaurantServiceTest {

    @Mock
    private RestaurantRepository restaurantRepository;

    @InjectMocks
    private RestaurantService restaurantService;

    @Test
    public void save() {

        Restaurant restaurant = this.generateRestaurant();

        Mockito.when(restaurantRepository.save(Mockito.any(Restaurant.class))).thenReturn(restaurant);

        Restaurant createRestaurant = restaurantService.save(new Restaurant());

        Assertions.assertEquals(restaurant, createRestaurant);
    }

    @Test
    public void getAll() {

        List<Restaurant> restaurantList = new ArrayList<>();
        restaurantList.add(this.generateRestaurant());

        Mockito.when(restaurantRepository.findAll()).thenReturn(restaurantList);

        List<Restaurant> createRestaurantList = restaurantService.getAll();

        Assertions.assertEquals(restaurantList, createRestaurantList);
    }

    @Test
    public void getById() {

        Restaurant restaurant = this.generateRestaurant();

        Mockito.when(restaurantRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(restaurant));

        Restaurant createRestaurant = restaurantService.getById(2);

        Assertions.assertEquals(restaurant, createRestaurant);
    }

    @Test
    public void update() {

        Restaurant restaurant = this.generateRestaurant();

        Mockito.when(restaurantRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(restaurant));

        Mockito.when(restaurantRepository.save(Mockito.any(Restaurant.class))).thenReturn(restaurant);

        Restaurant createRestaurant = restaurantService.update(new Restaurant());

        Assertions.assertEquals(restaurant, createRestaurant);
    }

    @Test
    public void delete() {

        restaurantRepository.deleteById(2L);
        Mockito.verify(restaurantRepository).deleteById(2L);

        String deleteRestaurant = restaurantService.delete(3);

        Assertions.assertEquals("SUCCESS", deleteRestaurant);
    }

    private Restaurant generateRestaurant() {
        return Restaurant.builder()
                .name("Hatay Medeniyetler Sofrası")
                .owner(User.builder().name("CZN Burak").build())
                .build();
    }

}