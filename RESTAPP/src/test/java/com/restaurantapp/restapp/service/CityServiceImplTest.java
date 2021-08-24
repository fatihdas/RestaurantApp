package com.restaurantapp.restapp.service;

import com.restaurantapp.restapp.model.converter.entity.todto.CityEntityToDtoConverter;
import com.restaurantapp.restapp.model.dto.CityDto;
import com.restaurantapp.restapp.model.entity.City;
import com.restaurantapp.restapp.repository.CityRepository;
import com.restaurantapp.restapp.service.impl.CityServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class CityServiceImplTest {

    @Mock
    private CityRepository cityRepository;

    @Mock
    private CityEntityToDtoConverter cityEntityToDtoConverter;

    @InjectMocks
    private CityServiceImpl cityServiceImpl;

    @Test
    public void getAll() {

        List<City> cityList = new ArrayList<>();
        cityList.add(this.generateCity());

        Mockito.when(cityEntityToDtoConverter.convert(Mockito.any(City.class)))
                .thenReturn(CityDto.builder().id(44).name("Malatya").build());
        Mockito.when(cityRepository.findAll()).thenReturn(cityList);

        List<CityDto> createCityList = cityServiceImpl.getAllCities();

        Assertions.assertEquals(cityList.get(0).getId(), createCityList.get(0).getId());
    }

    @Test
    public void getById() {

        City city = this.generateCity();

        Mockito.when(cityRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(city));
        Mockito.when(cityEntityToDtoConverter.convert(Mockito.any(City.class)))
                .thenReturn(CityDto.builder().id(44).build());

        CityDto createCity = cityServiceImpl.getCity(Mockito.anyLong());

        Assertions.assertEquals(44, createCity.getId());
    }

    private City generateCity() {

        return City.builder()
                .name("İstanbul")
                .id(44)
                .build();
    }

}