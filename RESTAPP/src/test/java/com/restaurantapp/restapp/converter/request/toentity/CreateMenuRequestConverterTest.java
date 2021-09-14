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
    private static final long BRANCH_ID = 2L;

    @Spy
    private CreateMenuRequestConverter createMenuRequestConverter;

    @Test
    public void whenConvertCalledByCreateMenuRequest_thenReturnMenu() {

        CreateMenuRequest menu = this.generateMenu();
        Menu menuActual = createMenuRequestConverter.convert(menu);

        Assertions.assertEquals(ID, menuActual.getId());
        Assertions.assertEquals(NAME, menuActual.getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenConvertCalledByNullRequest_thenThrowIllegalArgumentException(){
        createMenuRequestConverter.convert(null);
    }

    private CreateMenuRequest generateMenu() {

        return CreateMenuRequest.builder()
                .id(ID)
                .name(NAME)
                .branchId(BRANCH_ID)
                .build();
    }
}