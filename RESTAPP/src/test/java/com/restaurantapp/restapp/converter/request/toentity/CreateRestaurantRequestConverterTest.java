package com.restaurantapp.restapp.converter.request.toentity;

import com.restaurantapp.restapp.model.converter.create.request.CreateBranchRequestConverter;
import com.restaurantapp.restapp.model.converter.create.request.CreateRestaurantRequestConverter;
import com.restaurantapp.restapp.model.converter.create.request.UserIdToEntityConverter;
import com.restaurantapp.restapp.model.entity.Branch;
import com.restaurantapp.restapp.model.entity.Restaurant;
import com.restaurantapp.restapp.model.entity.User;
import com.restaurantapp.restapp.model.request.create.CreateBranchRequest;
import com.restaurantapp.restapp.model.request.create.CreateRestaurantRequest;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CreateRestaurantRequestConverterTest {

    private static final int ID = 11;
    private static final String NAME = "testRestaurant";
    private static final int USER_ID = 2;

    @Mock
    private CreateBranchRequestConverter createBranchRequestConverter;

    @Mock
    private UserIdToEntityConverter userIdToEntityConverter;

    @Spy
    @InjectMocks
    private CreateRestaurantRequestConverter createRestaurantRequestConverter;

    @Test
    public void convert() {

        CreateRestaurantRequest restaurant = this.generateRestaurant();
        Mockito.when(userIdToEntityConverter.convert(Mockito.anyLong())).thenReturn(User.builder().id(2).build());
        Mockito.when(createBranchRequestConverter.convert(Mockito.any(CreateBranchRequest.class)))
                .thenReturn(new Branch());
        Restaurant restaurantActual = createRestaurantRequestConverter.convert(restaurant);

        Assertions.assertEquals(ID, restaurantActual.getId());
        Assertions.assertEquals(NAME, restaurantActual.getName());
        Assertions.assertEquals(USER_ID, restaurantActual.getUser().getId());
    }

    private CreateRestaurantRequest generateRestaurant() {

        return CreateRestaurantRequest.builder()
                .id(ID)
                .name(NAME)
                .createBranchRequest(new CreateBranchRequest())
                .userId(USER_ID)
                .build();
    }
}