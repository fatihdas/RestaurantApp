package com.restaurantapp.restapp.service;

import com.restaurantapp.restapp.exception.RestaurantNotFoundException;
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

    public Restaurant save(Restaurant restaurant) {

        return restaurantRepository.save(restaurant);
    }

    public List<Restaurant> getAll() {

        return restaurantRepository.findAll();
    }

    public Restaurant getById(long id) {

        return restaurantRepository.findById(id).orElseThrow(() -> new RestaurantNotFoundException(id));
    }

    public Restaurant update(Restaurant restaurant) {

        restaurantRepository.findById(restaurant.getId()).orElseThrow(() -> new RestaurantNotFoundException(restaurant.getId()));
        return restaurantRepository.save(restaurant);
    }

    public String delete(long id) {

        restaurantRepository.deleteById(id);
        return "SUCCESS";
    }
}
