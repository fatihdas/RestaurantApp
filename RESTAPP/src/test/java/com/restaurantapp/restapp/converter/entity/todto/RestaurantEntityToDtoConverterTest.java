package com.restaurantapp.restapp.converter.entity.todto;

import com.restaurantapp.restapp.model.converter.entity.todto.BranchEntityToDtoConverter;
import com.restaurantapp.restapp.model.converter.entity.todto.RestaurantEntityToDtoConverter;
import com.restaurantapp.restapp.model.converter.entity.todto.UserEntityToDtoConverter;
import com.restaurantapp.restapp.model.dto.RestaurantDto;
import com.restaurantapp.restapp.model.entity.Branch;
import com.restaurantapp.restapp.model.entity.Restaurant;
import com.restaurantapp.restapp.model.entity.User;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;

@RunWith(MockitoJUnitRunner.class)
public class RestaurantEntityToDtoConverterTest {


    private static final int ID = 11;
    private static final String NAME = "testRestaurant";
    private static final long BRANCH_ID = 12L;

    @Spy
    private RestaurantEntityToDtoConverter restaurantEntityToDtoConverter;

    @Test
    public void whenConvertCalledByRestaurant_thenReturnRestaurantDto() {

        Restaurant restaurant = this.generateRestaurant();

        RestaurantDto restaurantActual = restaurantEntityToDtoConverter.convert(restaurant);
        Assertions.assertEquals(ID, restaurantActual.getId());
        Assertions.assertEquals(NAME, restaurantActual.getName());
        Assertions.assertEquals(BRANCH_ID, restaurant.getBranchList().get(0).getId());
    }

    private Restaurant generateRestaurant() {

        return Restaurant.builder()
                .id(ID)
                .name(NAME)
                .branchList(Arrays.asList(Branch.builder()
                        .id(BRANCH_ID)
                        .build()))
                .user(User.builder().build())
                .build();
    }
}