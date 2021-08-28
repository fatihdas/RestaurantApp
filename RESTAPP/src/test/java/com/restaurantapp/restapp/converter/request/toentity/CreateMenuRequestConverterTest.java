package com.restaurantapp.restapp.converter.request.toentity;

import com.restaurantapp.restapp.model.converter.create.request.CreateMealRequestConverter;
import com.restaurantapp.restapp.model.converter.create.request.CreateMenuRequestConverter;
import com.restaurantapp.restapp.model.entity.Meal;
import com.restaurantapp.restapp.model.entity.Menu;
import com.restaurantapp.restapp.model.request.create.CreateMealRequest;
import com.restaurantapp.restapp.model.request.create.CreateMenuRequest;
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
public class CreateMenuRequestConverterTest {

    private static final int ID = 55;
    private static final String NAME = "testmenu";
    @Mock
    private CreateMealRequestConverter createMealRequestConverter;

    @Spy
    @InjectMocks
    private CreateMenuRequestConverter createMenuRequestConverter;

    @Test
    public void convert() {

        CreateMenuRequest menu = this.generateMenu();
        Mockito.when(createMealRequestConverter.convert(Mockito.any(CreateMealRequest.class))).thenReturn(new Meal());
        Menu menuActual = createMenuRequestConverter.convert(menu);

        Assertions.assertEquals(ID, menuActual.getId());
        Assertions.assertEquals(NAME, menuActual.getName());
    }

    private CreateMenuRequest generateMenu() {

        return CreateMenuRequest.builder()
                .id(ID)
                .name(NAME)
                .createMealRequestList(new ArrayList<>())
                .build();
    }
}