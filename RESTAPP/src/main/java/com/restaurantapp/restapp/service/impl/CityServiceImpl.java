package com.restaurantapp.restapp.service.impl;

import com.restaurantapp.restapp.exception.CityNotFoundException;
import com.restaurantapp.restapp.model.converter.dto.toentity.CountyDtoToEntityConverter;
import com.restaurantapp.restapp.model.converter.entity.todto.CityEntityToDtoConverter;
import com.restaurantapp.restapp.model.dto.CityDto;
import com.restaurantapp.restapp.model.entity.City;
import com.restaurantapp.restapp.model.request.create.CreateCityRequest;
import com.restaurantapp.restapp.model.request.update.UpdateCityRequest;
import com.restaurantapp.restapp.repository.CityRepository;
import com.restaurantapp.restapp.service.CityService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
    private final CityEntityToDtoConverter cityEntityToDtoConverter;
    private final CountyDtoToEntityConverter countyDtoToEntityConverter;

    public CityServiceImpl(CityRepository cityRepository, CityEntityToDtoConverter cityEntityToDtoConverter, CountyDtoToEntityConverter countyDtoToEntityConverter) {
        this.cityRepository = cityRepository;
        this.cityEntityToDtoConverter = cityEntityToDtoConverter;
        this.countyDtoToEntityConverter = countyDtoToEntityConverter;
    }

    public CityDto createCity(CreateCityRequest request) {

        City city = City.builder()
                .name(request.getName())
                .countyList(request.getCountyDtoList().stream().map(countyDtoToEntityConverter::convert)
                        .collect(Collectors.toList()))
                .build();
        return cityEntityToDtoConverter.convert(cityRepository.save(city));
    }

    public List<CityDto> getAllCities() {

        return cityRepository.findAll().stream().map(cityEntityToDtoConverter::convert).collect(Collectors.toList());
    }

    public CityDto getCity(long id) {

        return cityEntityToDtoConverter.convert(cityRepository.findById(id)
                .orElseThrow(() -> new CityNotFoundException(id)));
    }

    public CityDto updateCity(UpdateCityRequest request, long id) {

        City city = cityRepository.findById(id).orElseThrow(() -> new CityNotFoundException());
        city.setName(request.getName());

        return cityEntityToDtoConverter.convert(cityRepository.save(city));
    }

    public void deleteCity(long id) {

        cityRepository.deleteById(id);
    }
}
