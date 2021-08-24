package com.restaurantapp.restapp.converter.request.toentity;

import com.restaurantapp.restapp.model.converter.create.request.CreateMenuRequestConverter;
import com.restaurantapp.restapp.model.entity.Menu;
import com.restaurantapp.restapp.model.request.create.CreateMenuRequest;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

@RunWith(MockitoJUnitRunner.class)
public class CreateMenuRequestConverterTest {

    @Spy
    @InjectMocks
    private CreateMenuRequestConverter createMenuRequestConverter;

    @Test
    public void convert() {

        Menu menuExpected = this.generateMenu();
        Mockito.doReturn(menuExpected).when(createMenuRequestConverter).convert(Mockito.any(CreateMenuRequest.class));
        Menu menuActual = createMenuRequestConverter.convert(new CreateMenuRequest());
        Assertions.assertEquals(menuExpected, menuActual);
    }

    private Menu generateMenu() {

        return Menu.builder()
                .id(55)
                .name("testmenu")
                .mealList(new ArrayList<>())
                .build();
    }
}