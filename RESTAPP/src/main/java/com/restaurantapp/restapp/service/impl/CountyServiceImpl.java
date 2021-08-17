package com.restaurantapp.restapp.service.impl;

import com.restaurantapp.restapp.exception.CountyNotFoundException;
import com.restaurantapp.restapp.model.dto.CountyDto;
import com.restaurantapp.restapp.model.converter.entity.todto.CountyEntityToDtoConverter;
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

        return countyRepository.findAll().stream().map(countyEntityToDtoConverter::convert).collect(Collectors.toList());
    }

    public CountyDto getCounty(long id) {

        return countyEntityToDtoConverter.convert(countyRepository.findById(id)
                .orElseThrow(() -> new CountyNotFoundException(id)));
    }

    public void deleteCounty(long id) {

        countyRepository.deleteById(id);
    }
}
