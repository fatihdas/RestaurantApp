package com.restaurantapp.restapp.service;

import com.restaurantapp.restapp.exception.CityNotFoundException;
import com.restaurantapp.restapp.model.City;
import com.restaurantapp.restapp.repository.CityRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityService {

    private final CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public City save(City city) {

        return cityRepository.save(city);
    }

    public List<City> getAll() {

        return cityRepository.findAll();
    }

    public City getById(long id) {

        return cityRepository.findById(id).orElseThrow(() -> new CityNotFoundException(id));
    }

    public City update(City city) {

        cityRepository.findById(city.getId()).orElseThrow(() -> new CityNotFoundException(city.getId()));
        return cityRepository.save(city);
    }

    public City delete(long id) {

        cityRepository.deleteById(id);

        return cityRepository.findById(id).orElseThrow(() -> new CityNotFoundException(id));
    }
}
