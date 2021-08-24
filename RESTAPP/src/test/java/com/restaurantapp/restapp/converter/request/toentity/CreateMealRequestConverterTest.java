package com.restaurantapp.restapp.converter.request.toentity;

import com.restaurantapp.restapp.model.converter.create.request.CreateMealRequestConverter;
import com.restaurantapp.restapp.model.entity.Meal;
import com.restaurantapp.restapp.model.request.create.CreateMealRequest;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CreateMealRequestConverterTest {

    @Spy
    @InjectMocks
    private CreateMealRequestConverter createMealRequestConverter;

    @Test
    public void convert() {

        Meal mealExpected = this.generateMeal();
        Mockito.doReturn(mealExpected).when(createMealRequestConverter).convert(Mockito.any(CreateMealRequest.class));
        Meal mealActual = createMealRequestConverter.convert(new CreateMealRequest());
        Assertions.assertEquals(mealExpected, mealActual);
    }

    private Meal generateMeal() {

        return Meal.builder()
                .id(12)
                .price(43)
                .name("testmeal")
                .build();
    }
}