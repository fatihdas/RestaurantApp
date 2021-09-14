package com.restaurantapp.restapp.converter.request.toentity;

import com.restaurantapp.restapp.model.converter.create.request.CreateRestaurantRequestConverter;
import com.restaurantapp.restapp.model.entity.Restaurant;
import com.restaurantapp.restapp.model.request.create.CreateRestaurantRequest;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CreateRestaurantRequestConverterTest {

    private static final int ID = 11;
    private static final String NAME = "testRestaurant";
    private static final int USER_ID = 2;

    @Spy
    private CreateRestaurantRequestConverter createRestaurantRequestConverter;

    @Test
    public void whenConvertCalledByCreateRestaurantRequest_thenReturnRestaurant() {

        CreateRestaurantRequest restaurant = this.generateRestaurant();
        Restaurant restaurantActual = createRestaurantRequestConverter.convert(restaurant);

        Assertions.assertEquals(ID, restaurantActual.getId());
        Assertions.assertEquals(NAME, restaurantActual.getName());
        Assertions.assertEquals(USER_ID, restaurantActual.getUser().getId());
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenConvertCalledByNullRequest_thenThrowIllegalArgumentException(){
        createRestaurantRequestConverter.convert(null);
    }

    private CreateRestaurantRequest generateRestaurant() {

        return CreateRestaurantRequest.builder()
                .id(ID)
                .name(NAME)
                .userId(USER_ID)
                .build();
    }
}