package com.restaurantapp.restapp.model.converter.entity.todto;

import com.restaurantapp.restapp.model.dto.MealDto;
import com.restaurantapp.restapp.model.dto.MenuDto;
import com.restaurantapp.restapp.model.entity.Menu;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MenuEntityToDtoConverter {

    private final MealEntityToDtoConverter mealEntityToDtoConverter;

    public MenuEntityToDtoConverter(MealEntityToDtoConverter mealEntityToDtoConverter) {
        this.mealEntityToDtoConverter = mealEntityToDtoConverter;
    }

    public MenuDto convert(Menu menu) {
        MenuDto menuDto = new MenuDto();
        menuDto.setId(menu.getId());
        menuDto.setName(menu.getName());
        List<MealDto> mealDtos = menu.getMealList().stream().map(mealEntityToDtoConverter::convert)
                .collect(Collectors.toList());
        menuDto.setMealDtoList(mealDtos);
        return menuDto;
    }
}
