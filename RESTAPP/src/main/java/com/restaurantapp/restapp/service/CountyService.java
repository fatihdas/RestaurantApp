package com.restaurantapp.restapp.service;

import com.restaurantapp.restapp.exception.CountyNotFoundException;
import com.restaurantapp.restapp.model.County;
import com.restaurantapp.restapp.repository.CountyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountyService {

    private final CountyRepository countyRepository;

    public CountyService(CountyRepository countyRepository) {
        this.countyRepository = countyRepository;
    }

    public County save(County county) {

        return countyRepository.save(county);
    }

    public List<County> getAll() {

        return countyRepository.findAll();
    }

    public County getById(long id) {

        return countyRepository.findById(id).orElseThrow(() -> new CountyNotFoundException(id));
    }

    public County update(County county) {

        countyRepository.findById(county.getId()).orElseThrow(() -> new CountyNotFoundException(county.getId()));
        return countyRepository.save(county);
    }

    public String delete(long id) {

        countyRepository.deleteById(id);
        return "SUCCESS";
    }
}
