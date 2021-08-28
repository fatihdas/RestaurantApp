package com.restaurantapp.restapp.converter.request.toentity;

import com.restaurantapp.restapp.model.converter.create.request.CreateMealRequestConverter;
import com.restaurantapp.restapp.model.entity.Meal;
import com.restaurantapp.restapp.model.request.create.CreateMealRequest;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CreateMealRequestConverterTest {

    private static final int ID = 12;
    private static final int PRICE = 43;
    private static final String NAME = "testmeal";

    @Spy
    private CreateMealRequestConverter createMealRequestConverter;

    @Test
    public void convert() {

        CreateMealRequest meal = this.generateMeal();
        Meal mealActual = createMealRequestConverter.convert(meal);

        Assertions.assertEquals(ID, mealActual.getId());
        Assertions.assertEquals(NAME, mealActual.getName());
        Assertions.assertEquals(PRICE, mealActual.getPrice());
    }

    private CreateMealRequest generateMeal() {

        return CreateMealRequest.builder()
                .id(ID)
                .price(PRICE)
                .name(NAME)
                .build();
    }
}