package com.restaurantapp.restapp.service;

import com.restaurantapp.restapp.model.County;
import com.restaurantapp.restapp.repository.CountyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountyService{

    private final CountyRepository countyRepository;

    public CountyService(CountyRepository countyRepository) {
        this.countyRepository = countyRepository;
    }

    public County save(County county){

        return countyRepository.save(county);
    }

    public List<County> getAll(){

        return countyRepository.findAll();
    }

    public County getById(long id){

        return countyRepository.findById(id).orElse(null);
    }

    public County update(County county, long id){

        County county1 = countyRepository.findById(id).orElse(null);

        county1.setId(county.getId());
        county1.setCity(county.getCity());
        county1.setName(county.getName());

        return county1;
    }

    public County delete(long id){

        countyRepository.deleteById(id);

        return countyRepository.findById(id).orElse(null);

    }
}
