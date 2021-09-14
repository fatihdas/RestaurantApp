package com.restaurantapp.restapp.service.impl;

import com.restaurantapp.restapp.exception.InvalidRoleException;
import com.restaurantapp.restapp.exception.RestaurantNotFoundException;
import com.restaurantapp.restapp.model.converter.create.request.CreateRestaurantRequestConverter;
import com.restaurantapp.restapp.model.converter.entity.todto.RestaurantEntityToDtoConverter;
import com.restaurantapp.restapp.model.dto.RestaurantDto;
import com.restaurantapp.restapp.model.entity.Restaurant;
import com.restaurantapp.restapp.model.entity.enumerated.UserRoles;
import com.restaurantapp.restapp.model.request.create.CreateRestaurantRequest;
import com.restaurantapp.restapp.model.request.update.UpdateRestaurantRequest;
import com.restaurantapp.restapp.repository.RestaurantRepository;
import com.restaurantapp.restapp.service.RestaurantService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final RestaurantEntityToDtoConverter restaurantEntityToDtoConverter;
    private final CreateRestaurantRequestConverter createRestaurantRequestConverter;
    private final UserServiceImpl userService;

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository,
                                 RestaurantEntityToDtoConverter restaurantEntityToDtoConverter,
                                 CreateRestaurantRequestConverter createRestaurantRequestConverter, UserServiceImpl userService) {
        this.restaurantRepository = restaurantRepository;
        this.restaurantEntityToDtoConverter = restaurantEntityToDtoConverter;
        this.createRestaurantRequestConverter = createRestaurantRequestConverter;
        this.userService = userService;
    }

    @Transactional
    public RestaurantDto createRestaurant(CreateRestaurantRequest request) {

        if (!(userService.hasRole(UserRoles.SELLER))) {
            throw new InvalidRoleException("Role is not valid!");
        }
        Restaurant restaurant = createRestaurantRequestConverter.convert(request);
        return restaurantEntityToDtoConverter.convert(restaurantRepository.save(restaurant));

    }

    public List<RestaurantDto> getAllRestaurants() {

        List<Restaurant> restaurantList = restaurantRepository.findAll();
        return restaurantList.stream().map(restaurantEntityToDtoConverter::convert)
                .collect(Collectors.toList());
    }

    public RestaurantDto getRestaurantDto(long id) {

        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(() -> new RestaurantNotFoundException(id));
        return restaurantEntityToDtoConverter.convert(restaurant);
    }

    @Override
    public Restaurant getRestaurant(long id) {
        return restaurantRepository.findById(id).orElseThrow(() -> new RestaurantNotFoundException());
    }

    public String updateRestaurant(UpdateRestaurantRequest request, long id) {

        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new RestaurantNotFoundException());

        restaurant.setName(restaurant.getName());
        return "Restaurant has been updated! id:" + id;
    }

    public void deleteRestaurant(long id) {

        restaurantRepository.deleteById(id);
    }
}
