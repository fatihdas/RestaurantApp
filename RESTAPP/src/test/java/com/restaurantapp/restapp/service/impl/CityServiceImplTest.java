package com.restaurantapp.restapp.service.impl;

import com.restaurantapp.restapp.exception.CityNotFoundException;
import com.restaurantapp.restapp.model.converter.entity.todto.CityEntityToDtoConverter;
import com.restaurantapp.restapp.model.dto.CityDto;
import com.restaurantapp.restapp.model.entity.City;
import com.restaurantapp.restapp.repository.CityRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class CityServiceImplTest {

    private static final long ID = 34l;
    private static final String NAME = "İstanbul";

    @Mock
    private CityRepository cityRepository;

    @Mock
    private CityEntityToDtoConverter cityEntityToDtoConverter;

    @InjectMocks
    private CityServiceImpl cityService;

    @Test
    public void whenGetAllCitiesCalled_thenReturnCityDtoList() {
        List<City> cityList = Arrays.asList(City.builder()
                .id(ID)
                .name(NAME)
                .build());
        CityDto cityDto = CityDto.builder()
                .id(ID)
                .name(NAME)
                .build();
        Mockito.when(cityRepository.findAll()).thenReturn(cityList);
        Mockito.when(cityEntityToDtoConverter.convert(Mockito.any(City.class))).thenReturn(cityDto);

        List<CityDto> result = cityService.getAllCities();
        Assertions.assertEquals(ID, result.get(0).getId());
        Assertions.assertEquals(NAME, result.get(0).getName());
    }

    @Test
    public void whenGetCityDtoCalledByValidCityId_thenReturnCityDto() {
        City city = City.builder()
                .name(NAME)
                .id(ID)
                .build();
        CityDto cityDto = CityDto.builder()
                .name(NAME)
                .id(ID)
                .build();
        Mockito.when(cityRepository.findById(ID)).thenReturn(java.util.Optional.ofNullable(city));
        Mockito.when(cityEntityToDtoConverter.convert(city)).thenReturn(cityDto);

        CityDto result = cityService.getCityDto(ID);
        Assertions.assertEquals(ID, result.getId());
        Assertions.assertEquals(NAME, result.getName());
    }

    @Test(expected = CityNotFoundException.class)
    public void whenGetCityDtoCalledByInValidCityId_thenThrowCityNotFoundException() {
        Mockito.when(cityRepository.findById(ID)).thenReturn(Optional.empty());
        cityService.getCityDto(ID);
    }

    @Test
    public void whenGetCityCalledByValidCityId_thenReturnCityEntity() {
        City city = City.builder()
                .id(ID)
                .name(NAME)
                .build();
        Mockito.when(cityRepository.findById(ID)).thenReturn(Optional.ofNullable(city));

        City result = cityService.getCity(ID);
        Assertions.assertEquals(ID, result.getId());
        Assertions.assertEquals(NAME, result.getName());
    }

    @Test(expected = CityNotFoundException.class)
    public void whenGetCityCalledByInValidCityId_thenThrowCityNotFoundException() {
        Mockito.when(cityRepository.findById(ID)).thenReturn(Optional.empty());
        cityService.getCity(ID);
    }
}