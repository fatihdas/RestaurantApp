package com.restaurantapp.restapp.model.converter.create.request;

import com.restaurantapp.restapp.model.entity.Branch;
import com.restaurantapp.restapp.model.entity.Restaurant;
import com.restaurantapp.restapp.model.request.create.CreateRestaurantRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CreateRestaurantRequestConverter {

    private final CreateBranchRequestConverter createBranchRequestConverter;
    private final UserIdToEntityConverter userIdToEntityConverter;

    public CreateRestaurantRequestConverter(CreateBranchRequestConverter createBranchRequestConverter, UserIdToEntityConverter userIdToEntityConverter) {
        this.createBranchRequestConverter = createBranchRequestConverter;
        this.userIdToEntityConverter = userIdToEntityConverter;
    }

    public Restaurant convert(CreateRestaurantRequest request) {

        List<Branch> branchList = new ArrayList<>();
        branchList.add(createBranchRequestConverter.convert(request.getCreateBranchRequest()));
        return Restaurant.builder()
                .id(request.getId())
                .name(request.getName())
                .user(userIdToEntityConverter.convert(request.getUserId()))
                .branchList(branchList)
                .build();
    }
}
