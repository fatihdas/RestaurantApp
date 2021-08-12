package com.restaurantapp.restapp.service;

import com.restaurantapp.restapp.model.dto.CityDto;
import com.restaurantapp.restapp.model.entity.City;
import com.restaurantapp.restapp.model.request.create.CreateCityRequest;
import com.restaurantapp.restapp.model.request.update.UpdateCityRequest;
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

    @InjectMocks
    private CityServiceImpl cityServiceImpl;

    @Test
    public void save() {

        City city = this.generateCity();

        Mockito.when(cityRepository.save(Mockito.any(City.class))).thenReturn(city);

        CityDto createCity = cityServiceImpl.createCity(new CreateCityRequest());

        Assertions.assertEquals(city, createCity);
    }

    @Test
    public void getAll() {

        List<City> cityList = new ArrayList<>();
        cityList.add(this.generateCity());

        Mockito.when(cityRepository.findAll()).thenReturn(cityList);

        List<CityDto> createCityList = cityServiceImpl.getAllCities();

        Assertions.assertEquals(cityList, createCityList);
    }

    @Test
    public void getById() {

        City city = this.generateCity();

        Mockito.when(cityRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(city));

        CityDto createCity = cityServiceImpl.getCity(2);

        Assertions.assertEquals(city, createCity);
    }

    @Test
    public void update() {

        City city = this.generateCity();

        Mockito.when(cityRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(city));

        Mockito.when(cityRepository.save(Mockito.any(City.class))).thenReturn(city);

        CityDto createCity = cityServiceImpl.updateCity(new UpdateCityRequest(),3);

        Assertions.assertEquals(city, createCity);
    }

    @Test
    public void delete() {

        cityRepository.deleteById(2L);
        Mockito.verify(cityServiceImpl).deleteCity(2L);
    }

    private City generateCity() {

        return City.builder()
                .name("İstanbul")
                .build();
    }

}