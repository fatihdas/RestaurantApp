package com.restaurantapp.restapp.service;

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

    public City save(City city){

        return cityRepository.save(city);
    }

    public List<City> getAll(){

        return cityRepository.findAll();
    }

    public City getById(long id){

        return cityRepository.findById(id).orElse(null);
    }

    public City update(City city, long id){

        City city1 = cityRepository.findById(id).orElse(null);

        city1.setId(city.getId());
        city1.setName(city.getName());

        cityRepository.save(city1);

        return city1;
    }

    public City delete(long id){

        cityRepository.deleteById(id);

        return cityRepository.findById(id).orElse(null);
    }
}
