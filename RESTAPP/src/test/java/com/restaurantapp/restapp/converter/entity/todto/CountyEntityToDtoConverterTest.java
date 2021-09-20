package com.restaurantapp.restapp.converter.entity.todto;

import com.restaurantapp.restapp.model.converter.entity.todto.CountyEntityToDtoConverter;
import com.restaurantapp.restapp.model.dto.CountyDto;
import com.restaurantapp.restapp.model.entity.City;
import com.restaurantapp.restapp.model.entity.County;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CountyEntityToDtoConverterTest {

    public static final int ID = 1;
    public static final String NAME = "Adana";
    private static final long CITY_ID = 34L;

    @Spy
    private CountyEntityToDtoConverter countyEntityToDtoConverter;

    @Test
    public void whenConvertCalledByCounty_thenReturnCountyDto() {

        County county = this.generateCounty();

        CountyDto countyActual = countyEntityToDtoConverter.convert(county);

        Assertions.assertEquals(ID, countyActual.getId());
        Assertions.assertEquals(NAME, countyActual.getName());
        Assertions.assertEquals(CITY_ID, countyActual.getCityId());

    }

    private County generateCounty() {

        return County.builder()
                .id(ID)
                .name(NAME)
                .city(City.builder()
                        .id(CITY_ID)
                        .build())
                .build();
    }
}