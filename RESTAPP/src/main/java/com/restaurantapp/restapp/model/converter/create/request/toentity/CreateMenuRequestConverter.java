package com.restaurantapp.restapp.model.converter.create.request.toentity;

import com.restaurantapp.restapp.model.entity.Menu;
import com.restaurantapp.restapp.model.request.create.CreateMenuRequest;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CreateMenuRequestConverter {

    private final CreateMealRequestConverter createMealRequestConverter;

    public CreateMenuRequestConverter(CreateMealRequestConverter createMealRequestConverter) {
        this.createMealRequestConverter = createMealRequestConverter;
    }

    public Menu convert(CreateMenuRequest request) {

        return Menu.builder()
                .id(request.getId())
                .name(request.getName())
                .mealList(request.getCreateMealRequestList().stream().map(createMealRequestConverter::convert)
                        .collect(Collectors.toList()))
                .build();
    }
}
