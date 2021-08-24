package com.restaurantapp.restapp.converter.request.toentity;

import com.restaurantapp.restapp.model.converter.create.request.CreateRestaurantRequestConverter;
import com.restaurantapp.restapp.model.entity.Restaurant;
import com.restaurantapp.restapp.model.entity.User;
import com.restaurantapp.restapp.model.request.create.CreateRestaurantRequest;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

@RunWith(MockitoJUnitRunner.class)
public class CreateRestaurantRequestConverterTest {

    @Spy
    @InjectMocks
    private CreateRestaurantRequestConverter createRestaurantRequestConverter;

    @Test
    public void convert() {

        Restaurant restaurantExpected = this.generateRestaurant();
        Mockito.doReturn(restaurantExpected).when(createRestaurantRequestConverter)
                .convert(Mockito.any(CreateRestaurantRequest.class));
        Restaurant restaurantActual = createRestaurantRequestConverter.convert(new CreateRestaurantRequest());
        Assertions.assertEquals(restaurantExpected, restaurantActual);
    }

    private Restaurant generateRestaurant() {

        return Restaurant.builder()
                .id(11)
                .name("testRestaurant")
                .branchList(new ArrayList<>())
                .user(User.builder().build())
                .build();
    }
}