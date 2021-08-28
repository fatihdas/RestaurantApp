package com.restaurantapp.restapp.converter.entity.todto;

import com.restaurantapp.restapp.model.converter.entity.todto.MealEntityToDtoConverter;
import com.restaurantapp.restapp.model.converter.entity.todto.MenuEntityToDtoConverter;
import com.restaurantapp.restapp.model.dto.MealDto;
import com.restaurantapp.restapp.model.dto.MenuDto;
import com.restaurantapp.restapp.model.entity.Meal;
import com.restaurantapp.restapp.model.entity.Menu;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

@RunWith(MockitoJUnitRunner.class)
public class MenuEntityToDtoConverterTest {

    private static final int ID = 55;
    private static final String NAME = "testmenu";

    @Mock
    private MealEntityToDtoConverter mealEntityToDtoConverter;

    @Spy
    @InjectMocks
    private MenuEntityToDtoConverter menuEntityToDtoConverter;

    @Test
    public void convert() {

        Menu menu = this.generateMenu();
//        Mockito.when(mealEntityToDtoConverter.convert(Mockito.any(Meal.class))).thenReturn(new MealDto());
        MenuDto menuActual = menuEntityToDtoConverter.convert(menu);

        Assertions.assertEquals(ID, menuActual.getId());
        Assertions.assertEquals(NAME, menuActual.getName());
    }

    private Menu generateMenu(){

        return Menu.builder()
                .id(ID)
                .name(NAME)
                .mealList(new ArrayList<>())
                .build();
    }
}