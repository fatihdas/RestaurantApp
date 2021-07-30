package com.restaurantapp.restapp.service;

import com.restaurantapp.restapp.model.City;
import com.restaurantapp.restapp.model.County;
import com.restaurantapp.restapp.repository.CountyRepository;
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
public class CountyServiceTest {

    @Mock
    private CountyRepository countyRepository;

    @InjectMocks
    private CountyService countyService;

    @Test
    public void save() {

        County county = this.generateCounty();

        Mockito.when(countyRepository.save(Mockito.any(County.class))).thenReturn(county);

        County createCounty = countyService.save(new County());

        Assertions.assertEquals(county, createCounty);
    }

    @Test
    public void getAll() {

        List<County> countyList = new ArrayList<>();
        countyList.add(this.generateCounty());

        Mockito.when(countyRepository.findAll()).thenReturn(countyList);

        List<County> createCountyList = countyService.getAll();

        Assertions.assertEquals(countyList, createCountyList);
    }

    @Test
    public void getById() {

        County county = this.generateCounty();

        Mockito.when(countyRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(county));

        County createCounty = countyService.getById(2);

        Assertions.assertEquals(county, createCounty);
    }

    @Test
    public void update() {

        County county = this.generateCounty();

        Mockito.when(countyRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(county));

        Mockito.when(countyRepository.save(Mockito.any(County.class))).thenReturn(county);

        County createCounty = countyService.update(new County());

        Assertions.assertEquals(county, createCounty);
    }

    @Test
    public void delete() {

        countyRepository.deleteById(2L);
        Mockito.verify(countyRepository).deleteById(2L);

        String deleteCounty = countyService.delete(3);

        Assertions.assertEquals("SUCCESS", deleteCounty);
    }

    private County generateCounty() {
        return County.builder()
                .name("Pendik")
                .city(City.builder().name("İstanbul").build())
                .build();
    }
}