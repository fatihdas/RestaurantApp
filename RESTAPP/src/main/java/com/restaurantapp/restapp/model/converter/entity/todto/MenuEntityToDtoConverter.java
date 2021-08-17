package com.restaurantapp.restapp.model.converter.entity.todto;

import com.restaurantapp.restapp.model.dto.MenuDto;
import com.restaurantapp.restapp.model.entity.Menu;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class MenuEntityToDtoConverter {

    private final BranchEntityToDtoConverter branchEntityToDtoConverter;
    private final MealEntityToDtoConverter mealEntityToDtoConverter;

    public MenuEntityToDtoConverter(@Lazy BranchEntityToDtoConverter branchEntityToDtoConverter, @Lazy MealEntityToDtoConverter mealEntityToDtoConverter) {
        this.branchEntityToDtoConverter = branchEntityToDtoConverter;
        this.mealEntityToDtoConverter = mealEntityToDtoConverter;
    }

    public MenuDto convert(Menu menu) {

        return MenuDto.builder()
                .id(menu.getId())
                .build();
    }
}
