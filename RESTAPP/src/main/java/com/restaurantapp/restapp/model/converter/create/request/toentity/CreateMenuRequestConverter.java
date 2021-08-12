package com.restaurantapp.restapp.model.converter.create.request.toentity;

import com.restaurantapp.restapp.model.converter.dto.toentity.MealDtoToEntityConverter;
import com.restaurantapp.restapp.model.entity.Menu;
import com.restaurantapp.restapp.model.request.create.CreateMenuRequest;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CreateMenuRequestConverter {

    private final MealDtoToEntityConverter mealDtoToEntityConverter;

    public CreateMenuRequestConverter(MealDtoToEntityConverter mealDtoToEntityConverter) {
        this.mealDtoToEntityConverter = mealDtoToEntityConverter;
    }

    public Menu convert(CreateMenuRequest request) {

        return Menu.builder()
                .name(request.getName())
                .mealList(request.getMealDtoList().stream().map(mealDtoToEntityConverter::convert)
                        .collect(Collectors.toList()))
                .build();
    }
}
