package com.restaurantapp.restapp.model.converter.dto.toentity;

import com.restaurantapp.restapp.model.dto.MenuDto;
import com.restaurantapp.restapp.model.entity.Menu;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class MenuDtoToEntityConverter {

    private final MealDtoToEntityConverter mealDtoToEntityConverter;

    public MenuDtoToEntityConverter(MealDtoToEntityConverter mealDtoToEntityConverter) {
        this.mealDtoToEntityConverter = mealDtoToEntityConverter;
    }

    public Menu convert(MenuDto menuDto) {

        return Menu.builder()
                .name(menuDto.getName())
                .build();
    }
}
