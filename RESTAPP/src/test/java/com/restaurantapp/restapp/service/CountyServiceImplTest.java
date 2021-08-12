package com.restaurantapp.restapp.service;

import com.restaurantapp.restapp.model.dto.CountyDto;
import com.restaurantapp.restapp.model.entity.City;
import com.restaurantapp.restapp.model.entity.County;
import com.restaurantapp.restapp.model.request.create.CreateCountyRequest;
import com.restaurantapp.restapp.model.request.update.UpdateCountyRequest;
import com.restaurantapp.restapp.repository.CountyRepository;
import com.restaurantapp.restapp.service.impl.CountyServiceImpl;
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
public class CountyServiceImplTest {

    @Mock
    private CountyRepository countyRepository;

    @InjectMocks
    private CountyServiceImpl countyServiceImpl;

    @Test
    public void save() {

        County county = this.generateCounty();

        Mockito.when(countyRepository.save(Mockito.any(County.class))).thenReturn(county);

        CountyDto createCounty = countyServiceImpl.createCounty(new CreateCountyRequest());

        Assertions.assertEquals(county, createCounty);
    }

    @Test
    public void getAll() {

        List<County> countyList = new ArrayList<>();
        countyList.add(this.generateCounty());

        Mockito.when(countyRepository.findAll()).thenReturn(countyList);

        List<CountyDto> createCountyList = countyServiceImpl.getAllCounties();

        Assertions.assertEquals(countyList, createCountyList);
    }

    @Test
    public void getById() {

        County county = this.generateCounty();

        Mockito.when(countyRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(county));

        CountyDto createCounty = countyServiceImpl.getCounty(2);

        Assertions.assertEquals(county, createCounty);
    }

    @Test
    public void update() {

        County county = this.generateCounty();

        Mockito.when(countyRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(county));

        Mockito.when(countyRepository.save(Mockito.any(County.class))).thenReturn(county);

        CountyDto createCounty = countyServiceImpl.updateCounty(new UpdateCountyRequest(),3);

        Assertions.assertEquals(county, createCounty);
    }

    @Test
    public void delete() {

        countyRepository.deleteById(2L);
        Mockito.verify(countyServiceImpl).deleteCounty(2L);
    }

    private County generateCounty() {
        return County.builder()
                .name("Pendik")
                .build();
    }
}