package com.restaurantapp.restapp.service.impl;

import com.restaurantapp.restapp.exception.CountyNotFoundException;
import com.restaurantapp.restapp.model.converter.entity.todto.CountyEntityToDtoConverter;
import com.restaurantapp.restapp.model.dto.CountyDto;
import com.restaurantapp.restapp.model.entity.County;
import com.restaurantapp.restapp.repository.CountyRepository;
import com.restaurantapp.restapp.service.CountyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountyServiceImpl implements CountyService {

    private final CountyRepository countyRepository;
    private final CountyEntityToDtoConverter countyEntityToDtoConverter;

    public CountyServiceImpl(CountyRepository countyRepository, CountyEntityToDtoConverter countyEntityToDtoConverter) {
        this.countyRepository = countyRepository;
        this.countyEntityToDtoConverter = countyEntityToDtoConverter;
    }

    public List<CountyDto> getAllCounties() {

        List<County> countyList = countyRepository.findAll();
        return countyList.stream().map(countyEntityToDtoConverter::convert).collect(Collectors.toList());
    }

    public CountyDto getCountyDto(long id) {

        County county = countyRepository.findById(id).orElseThrow(() -> new CountyNotFoundException());
        return countyEntityToDtoConverter.convert(county);
    }

    @Override
    public County getCounty(long id) {
        return countyRepository.findById(id).orElseThrow(() -> new CountyNotFoundException());
    }

}
