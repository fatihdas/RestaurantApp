//package com.restaurantapp.restapp.model.converter.dto.toentity;
//
//import com.restaurantapp.restapp.model.dto.UserDto;
//import com.restaurantapp.restapp.model.entity.User;
//import org.springframework.stereotype.Component;
//
//@Component
//public class UserDtoToEntityConverter {
//
//    private final RestaurantDtoToEntityConverter restaurantDtoToEntityConverter;
//
//    public UserDtoToEntityConverter(RestaurantDtoToEntityConverter restaurantDtoToEntityConverter) {
//        this.restaurantDtoToEntityConverter = restaurantDtoToEntityConverter;
//    }
//
//    public User convert(UserDto userDto) {
//
//        return User.builder()
//                .id(userDto.getId())
//                .roles(userDto.getRolesList())
//                .name(userDto.getName())
//                .password(userDto.getPassword())
//                .email(userDto.getEmail())
//                .restaurantList()
//                .build();
//    }
//}
