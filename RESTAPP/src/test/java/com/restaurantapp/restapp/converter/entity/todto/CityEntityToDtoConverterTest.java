package com.restaurantapp.restapp.converter.entity.todto;

import com.restaurantapp.restapp.model.converter.entity.todto.CityEntityToDtoConverter;
import com.restaurantapp.restapp.model.dto.CityDto;
import com.restaurantapp.restapp.model.entity.City;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CityEntityToDtoConverterTest {

    private static final int ID = 38;
    private static final String NAME = "Kayseri";

    @Spy
    private CityEntityToDtoConverter cityEntityToDtoConverter;

    @Test
    public void whenConvertCalledByCity_thenReturnCityDto() {
        City cityExpected = this.generateCity();
        CityDto cityActual = cityEntityToDtoConverter.convert(cityExpected);

        Assertions.assertEquals(ID, cityActual.getId());
        Assertions.assertEquals(NAME, cityActual.getName());
    }

    private City generateCity(){

        return City.builder()
                .id(ID)
                .name(NAME)
                .build();
    }
}