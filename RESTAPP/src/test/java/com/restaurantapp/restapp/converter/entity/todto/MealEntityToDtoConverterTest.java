package com.restaurantapp.restapp.converter.entity.todto;

import com.restaurantapp.restapp.model.converter.entity.todto.MealEntityToDtoConverter;
import com.restaurantapp.restapp.model.dto.MealDto;
import com.restaurantapp.restapp.model.entity.Meal;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MealEntityToDtoConverterTest {

    private static final int ID = 1;
    private static final String NAME = "testMeal";
    private static final int PRICE = 11;

    @Spy
    private MealEntityToDtoConverter mealEntityToDtoConverter;

    @Test
    public void convert() {

        Meal meal = this.generateMeal();
        MealDto mealActual = mealEntityToDtoConverter.convert(meal);

        Assertions.assertEquals(ID, mealActual.getId());
        Assertions.assertEquals(NAME, mealActual.getName());
        Assertions.assertEquals(PRICE, mealActual.getPrice());
    }

    private Meal generateMeal(){

        return Meal.builder()
                .id(ID)
                .name(NAME)
                .price(PRICE)
                .build();
    }
}