package com.restaurantapp.restapp.service.impl;

import com.restaurantapp.restapp.exception.CityNotFoundException;
import com.restaurantapp.restapp.model.converter.entity.todto.CityEntityToDtoConverter;
import com.restaurantapp.restapp.model.dto.CityDto;
import com.restaurantapp.restapp.model.entity.City;
import com.restaurantapp.restapp.repository.CityRepository;
import com.restaurantapp.restapp.service.CityService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
    private final CityEntityToDtoConverter cityEntityToDtoConverter;

    public CityServiceImpl(CityRepository cityRepository, CityEntityToDtoConverter cityEntityToDtoConverter) {
        this.cityRepository = cityRepository;
        this.cityEntityToDtoConverter = cityEntityToDtoConverter;
    }

    public List<CityDto> getAllCities() {

        return cityRepository.findAll().stream().map(cityEntityToDtoConverter::convert).collect(Collectors.toList());
    }

    public CityDto getCityDto(long id) {

        return cityEntityToDtoConverter.convert(cityRepository.findById(id)
                .orElseThrow(() -> new CityNotFoundException(id)));
    }

    @Override
    public City getCity(long id) {
        return cityRepository.findById(id).orElseThrow(() -> new CityNotFoundException(id));
    }

    public void deleteCity(long id) {

        cityRepository.deleteById(id);
    }
}
