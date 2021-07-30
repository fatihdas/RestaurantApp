package com.restaurantapp.restapp.service;

import com.restaurantapp.restapp.model.City;
import com.restaurantapp.restapp.repository.CityRepository;
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
public class CityServiceTest {

    @Mock
    private CityRepository cityRepository;

    @InjectMocks
    private CityService cityService;

    @Test
    public void save() {

        City city = this.generateCity();

        Mockito.when(cityRepository.save(Mockito.any(City.class))).thenReturn(city);

        City createCity = cityService.save(new City());

        Assertions.assertEquals(city, createCity);
    }

    @Test
    public void getAll() {

        List<City> cityList = new ArrayList<>();
        cityList.add(this.generateCity());

        Mockito.when(cityRepository.findAll()).thenReturn(cityList);

        List<City> createCityList = cityService.getAll();

        Assertions.assertEquals(cityList, createCityList);
    }

    @Test
    public void getById() {

        City city = this.generateCity();

        Mockito.when(cityRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(city));

        City createCity = cityService.getById(2);

        Assertions.assertEquals(city, createCity);
    }

    @Test
    public void update() {

        City city = this.generateCity();

        Mockito.when(cityRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(city));

        Mockito.when(cityRepository.save(Mockito.any(City.class))).thenReturn(city);

        City createCity = cityService.update(new City());

        Assertions.assertEquals(city, createCity);
    }

    @Test
    public void delete() {

        cityRepository.deleteById(2L);
        Mockito.verify(cityRepository).deleteById(2L);

        String deleteCity = cityService.delete(Mockito.anyLong());

        Assertions.assertEquals("SUCCESS", deleteCity);
    }

    private City generateCity() {

        return City.builder()
                .name("İstanbul")
                .build();
    }

}