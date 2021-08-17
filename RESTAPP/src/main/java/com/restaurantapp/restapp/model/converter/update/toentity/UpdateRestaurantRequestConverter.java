package com.restaurantapp.restapp.model.converter.update.toentity;

import com.restaurantapp.restapp.model.converter.dto.toentity.BranchDtoToEntityConverter;
import com.restaurantapp.restapp.model.entity.Restaurant;
import com.restaurantapp.restapp.model.request.update.UpdateRestaurantRequest;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UpdateRestaurantRequestConverter {

    private final BranchDtoToEntityConverter branchDtoToEntityConverter;

    public UpdateRestaurantRequestConverter(BranchDtoToEntityConverter branchDtoToEntityConverter) {
        this.branchDtoToEntityConverter = branchDtoToEntityConverter;
    }

    public Restaurant convert(UpdateRestaurantRequest request) {

        return Restaurant.builder()
                .name(request.getName())
                .build();
    }
}
