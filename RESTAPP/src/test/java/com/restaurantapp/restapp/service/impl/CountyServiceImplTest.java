package com.restaurantapp.restapp.service.impl;

import com.restaurantapp.restapp.exception.CountyNotFoundException;
import com.restaurantapp.restapp.model.converter.entity.todto.CountyEntityToDtoConverter;
import com.restaurantapp.restapp.model.dto.CountyDto;
import com.restaurantapp.restapp.model.entity.County;
import com.restaurantapp.restapp.repository.CountyRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class CountyServiceImplTest {
    private static final long ID = 1l;
    private static final String NAME = "Abana";
    @Mock
    private CountyRepository countyRepository;

    @Mock
    private CountyEntityToDtoConverter countyEntityToDtoConverter;

    @InjectMocks
    private CountyServiceImpl countyService;

    @Test
    public void whenGetAllCountiesCalled_thenReturnCountyDtoList() {
        List<County> countyList = Collections.singletonList(County.builder()
                .id(ID)
                .name(NAME)
                .build());
        CountyDto countyDto = CountyDto.builder()
                .id(ID)
                .name(NAME)
                .build();
        Mockito.when(countyRepository.findAll()).thenReturn(countyList);
        Mockito.when(countyEntityToDtoConverter.convert(Mockito.any(County.class))).thenReturn(countyDto);

        List<CountyDto> result = countyService.getAllCounties();
        Assertions.assertEquals(ID, result.get(0).getId());
        Assertions.assertEquals(NAME, result.get(0).getName());
    }

    @Test
    public void whenGetCountyDtoCalledByValidCountyId_thenReturnCountyDto() {
        County county = County.builder()
                .id(ID)
                .name(NAME)
                .build();
        CountyDto countyDto = CountyDto.builder()
                .name(NAME)
                .id(ID)
                .build();
        Mockito.when(countyRepository.findById(ID)).thenReturn(java.util.Optional.ofNullable(county));
        Mockito.when(countyEntityToDtoConverter.convert(county)).thenReturn(countyDto);

        CountyDto result = countyService.getCountyDto(ID);
        Assertions.assertEquals(ID, result.getId());
        Assertions.assertEquals(NAME, result.getName());
    }

    @Test(expected = CountyNotFoundException.class)
    public void whenGetCountyDtoCalledByInValidCountyId_thenThrowCountyNotFoundException() {
        Mockito.when(countyRepository.findById(ID)).thenReturn(Optional.empty());
        countyService.getCountyDto(ID);
    }

    @Test
    public void whenGetCountyCalledByValidCountyId_thenReturnCounty() {
        County county = County.builder()
                .name(NAME)
                .id(ID)
                .build();
        Mockito.when(countyRepository.findById(ID)).thenReturn(Optional.ofNullable(county));

        County result = countyService.getCounty(ID);
        Assertions.assertEquals(ID, result.getId());
        Assertions.assertEquals(NAME, result.getName());
    }

    @Test(expected = CountyNotFoundException.class)
    public void whenGetCountyCalledByInValidCountyId_thenThrowCountyNotFoundException() {
        Mockito.when(countyRepository.findById(ID)).thenReturn(Optional.empty());
        countyService.getCounty(ID);
    }
}