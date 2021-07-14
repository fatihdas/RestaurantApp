package com.restaurantapp.restapp.service;

import com.restaurantapp.restapp.model.Restaurant;
import com.restaurantapp.restapp.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public Restaurant save(Restaurant restaurant){

        return restaurantRepository.save(restaurant);
    }

    public List<Restaurant> getAll(){

        return restaurantRepository.findAll();
    }

    public Restaurant getById(long id){

        return restaurantRepository.findById(id).orElse(null);
    }

    public Restaurant update(Restaurant restaurant,long id){

        Restaurant restaurant1 = restaurantRepository.findById(id).orElse(null);
        restaurant1.setId(restaurant.getId());
        restaurant1.setName(restaurant.getName());
        restaurant1.setOwner(restaurant.getOwner());
        restaurantRepository.save(restaurant1);

        return restaurant1;
    }

    public Restaurant delete(long id){

        restaurantRepository.deleteById(id);

        return restaurantRepository.findById(id).orElse(null);

    }
}
