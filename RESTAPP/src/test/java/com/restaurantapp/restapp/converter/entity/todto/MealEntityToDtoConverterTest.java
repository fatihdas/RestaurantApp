package com.restaurantapp.restapp.converter.entity.todto;

import com.restaurantapp.restapp.model.converter.entity.todto.MealEntityToDtoConverter;
import com.restaurantapp.restapp.model.dto.MealDto;
import com.restaurantapp.restapp.model.entity.Meal;
import com.restaurantapp.restapp.model.entity.Menu;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MealEntityToDtoConverterTest {

    private static final int ID = 1;
    private static final String NAME = "testMeal";
    private static final int PRICE = 11;
    private static final long MENU_ID = 1L;

    @Spy
    private MealEntityToDtoConverter mealEntityToDtoConverter;

    @Test
    public void whenConvertCalledByMeal_thenReturnMealDto() {

        Meal meal = this.generateMeal();
        MealDto mealActual = mealEntityToDtoConverter.convert(meal);

        Assertions.assertEquals(ID, mealActual.getId());
        Assertions.assertEquals(NAME, mealActual.getName());
        Assertions.assertEquals(PRICE, mealActual.getPrice());
        Assertions.assertEquals(MENU_ID, mealActual.getMenuId());
    }

    private Meal generateMeal() {

        return Meal.builder()
                .id(ID)
                .name(NAME)
                .menu(Menu.builder()
                        .id(MENU_ID)
                        .build())
                .price(PRICE)
                .build();
    }
}