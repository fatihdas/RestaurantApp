package com.restaurantapp.restapp.model.converter.create.request;

import com.restaurantapp.restapp.model.entity.Branch;
import com.restaurantapp.restapp.model.entity.Menu;
import com.restaurantapp.restapp.model.request.create.CreateMenuRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CreateMenuRequestConverter {

    public Menu convert(CreateMenuRequest request) {
        if (request == null || request.getBranchId() == 0) {
            throw new IllegalArgumentException("invalid request!");
        }
        Menu menu = new Menu();
        menu.setName(request.getName());
        menu.setBranch(Branch.builder()
                .id(request.getBranchId())
                .build());
        menu.setMealList(new ArrayList<>());
        menu.setId(request.getId());

        return menu;
    }
}
