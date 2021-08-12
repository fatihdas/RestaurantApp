package com.restaurantapp.restapp.model.converter.create.request.toentity;

import com.restaurantapp.restapp.model.converter.dto.toentity.BranchDtoToEntityConverter;
import com.restaurantapp.restapp.model.converter.dto.toentity.UserDtoToEntityConverter;
import com.restaurantapp.restapp.model.entity.Restaurant;
import com.restaurantapp.restapp.model.request.create.CreateRestaurantRequest;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CreateRestaurantRequestConverter {

    private final UserDtoToEntityConverter userDtoToEntityConverter;
    private final BranchDtoToEntityConverter branchDtoToEntityConverter;

    public CreateRestaurantRequestConverter(UserDtoToEntityConverter userDtoToEntityConverter, BranchDtoToEntityConverter branchDtoToEntityConverter) {
        this.userDtoToEntityConverter = userDtoToEntityConverter;
        this.branchDtoToEntityConverter = branchDtoToEntityConverter;
    }

    public Restaurant convert(CreateRestaurantRequest request){

        return Restaurant.builder()
                .name(request.getName())
                .user(userDtoToEntityConverter.convert(request.getUserDto()))
                .branchList(request.getBranchDtoList().stream().map(branchDtoToEntityConverter::convert)
                        .collect(Collectors.toList()))
                .build();
    }
}
