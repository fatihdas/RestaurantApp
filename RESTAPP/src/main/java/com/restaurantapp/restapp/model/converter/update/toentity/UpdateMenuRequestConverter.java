package com.restaurantapp.restapp.model.converter.update.toentity;

import com.restaurantapp.restapp.model.converter.dto.toentity.MealDtoToEntityConverter;
import com.restaurantapp.restapp.model.entity.Menu;
import com.restaurantapp.restapp.model.request.update.UpdateMenuRequest;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UpdateMenuRequestConverter {

    private final MealDtoToEntityConverter mealDtoToEntityConverter;

    public UpdateMenuRequestConverter(MealDtoToEntityConverter mealDtoToEntityConverter) {
        this.mealDtoToEntityConverter = mealDtoToEntityConverter;
    }

    public Menu convert(UpdateMenuRequest request) {

        return Menu.builder()
                .name(request.getName())
                .mealList(request.getMealDtoList().stream().map(mealDtoToEntityConverter::convert)
                        .collect(Collectors.toList()))
                .build();
    }
}
