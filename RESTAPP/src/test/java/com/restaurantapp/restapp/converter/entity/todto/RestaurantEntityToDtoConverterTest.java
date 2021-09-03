//package com.restaurantapp.restapp.converter.entity.todto;
//
//import com.restaurantapp.restapp.model.converter.entity.todto.BranchEntityToDtoConverter;
//import com.restaurantapp.restapp.model.converter.entity.todto.RestaurantEntityToDtoConverter;
//import com.restaurantapp.restapp.model.converter.entity.todto.UserEntityToDtoConverter;
//import com.restaurantapp.restapp.model.dto.BranchDto;
//import com.restaurantapp.restapp.model.dto.RestaurantDto;
//import com.restaurantapp.restapp.model.dto.UserDto;
//import com.restaurantapp.restapp.model.entity.Branch;
//import com.restaurantapp.restapp.model.entity.Restaurant;
//import com.restaurantapp.restapp.model.entity.User;
//import org.junit.Test;
//import org.junit.jupiter.api.Assertions;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.MockitoJUnitRunner;
//
//import java.util.ArrayList;
//
//@RunWith(MockitoJUnitRunner.class)
//public class RestaurantEntityToDtoConverterTest {
//
//
//    private static final int ID = 11;
//    private static final String NAME = "testRestaurant";
//
//    @Mock
//    private BranchEntityToDtoConverter branchEntityToDtoConverter;
//
//    @Mock
//    private UserEntityToDtoConverter userEntityToDtoConverter;
//
//    @InjectMocks
//    private RestaurantEntityToDtoConverter restaurantEntityToDtoConverter;
//
//
//    @Test
//    public void convert() {
//
////        Mockito.when(userEntityToDtoConverter.convert(Mockito.any(User.class))).thenReturn(new UserDto());
////        Mockito.when(branchEntityToDtoConverter.convert(Mockito.any(Branch.class))).thenReturn(new BranchDto());
//        Restaurant restaurant = this.generateRestaurant();
//
//        RestaurantDto restaurantActual = restaurantEntityToDtoConverter.convert(restaurant);
//        Assertions.assertEquals(ID, restaurantActual.getId());
//        Assertions.assertEquals(NAME, restaurantActual.getName());
//    }
//
//    private Restaurant generateRestaurant() {
//
//        return Restaurant.builder()
//                .id(ID)
//                .name(NAME)
//                .branchList(new ArrayList<>())
//                .user(User.builder().build())
//                .build();
//    }
//}