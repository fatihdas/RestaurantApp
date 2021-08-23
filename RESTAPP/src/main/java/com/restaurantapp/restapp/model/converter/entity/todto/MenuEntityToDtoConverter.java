package com.restaurantapp.restapp.model.converter.entity.todto;

import com.restaurantapp.restapp.model.dto.MenuDto;
import com.restaurantapp.restapp.model.entity.Menu;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class MenuEntityToDtoConverter {

    private final MealEntityToDtoConverter mealEntityToDtoConverter;

    public MenuEntityToDtoConverter(MealEntityToDtoConverter mealEntityToDtoConverter) {
        this.mealEntityToDtoConverter = mealEntityToDtoConverter;
    }

    public MenuDto convert(Menu menu) {

        return MenuDto.builder()
                .id(menu.getId())
                .name(menu.getName())
                .mealDtoList(menu.getMealList().stream().map(mealEntityToDtoConverter::convert)
                        .collect(Collectors.toList()))
                .build();
    }
}
