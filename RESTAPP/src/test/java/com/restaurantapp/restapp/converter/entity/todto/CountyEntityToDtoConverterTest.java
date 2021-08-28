package com.restaurantapp.restapp.converter.entity.todto;

import com.restaurantapp.restapp.model.converter.entity.todto.CountyEntityToDtoConverter;
import com.restaurantapp.restapp.model.dto.CountyDto;
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

    @Spy
    private CountyEntityToDtoConverter countyEntityToDtoConverter;

    @Test
    public void convert() {

        County county = this.generateCounty();

        CountyDto countyActual = countyEntityToDtoConverter.convert(county);

        Assertions.assertEquals(ID, countyActual.getId());
        Assertions.assertEquals(NAME, countyActual.getName());

    }

    private County generateCounty() {

        return County.builder()
                .id(ID)
                .name(NAME)
                .build();
    }
}