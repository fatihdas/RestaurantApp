package com.restaurantapp.restapp.service.impl;

import com.restaurantapp.restapp.exception.RestaurantNotFoundException;
import com.restaurantapp.restapp.model.converter.create.request.toentity.CreateRestaurantRequestConverter;
import com.restaurantapp.restapp.model.converter.entity.todto.RestaurantEntityToDtoConverter;
import com.restaurantapp.restapp.model.converter.update.toentity.UpdateRestaurantRequestConverter;
import com.restaurantapp.restapp.model.dto.CountyDto;
import com.restaurantapp.restapp.model.dto.RestaurantDto;
import com.restaurantapp.restapp.model.entity.Restaurant;
import com.restaurantapp.restapp.model.request.create.CreateRestaurantRequest;
import com.restaurantapp.restapp.model.request.update.UpdateRestaurantRequest;
import com.restaurantapp.restapp.repository.RestaurantRepository;
import com.restaurantapp.restapp.service.RestaurantService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final RestaurantEntityToDtoConverter restaurantEntityToDtoConverter;
    private final CreateRestaurantRequestConverter createRestaurantRequestConverter;
    private final UpdateRestaurantRequestConverter updateRestaurantRequestConverter;

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository,
                                 RestaurantEntityToDtoConverter restaurantEntityToDtoConverter,
                                 CreateRestaurantRequestConverter createRestaurantRequestConverter,
                                 UpdateRestaurantRequestConverter updateRestaurantRequestConverter) {
        this.restaurantRepository = restaurantRepository;
        this.restaurantEntityToDtoConverter = restaurantEntityToDtoConverter;
        this.createRestaurantRequestConverter = createRestaurantRequestConverter;
        this.updateRestaurantRequestConverter = updateRestaurantRequestConverter;
    }

    public RestaurantDto createRestaurant(CreateRestaurantRequest request) {

        return restaurantEntityToDtoConverter.convert(restaurantRepository.save(createRestaurantRequestConverter
                .convert(request)));
    }

    public List<RestaurantDto> getAllRestaurants() {

        return restaurantRepository.findAll().stream().map(restaurantEntityToDtoConverter::convert)
                .collect(Collectors.toList());
    }

    public RestaurantDto getRestaurant(long id) {

        return restaurantEntityToDtoConverter.convert(restaurantRepository.findById(id)
                .orElseThrow(() -> new RestaurantNotFoundException(id)));
    }

//    public List<RestaurantDto> getRestaurantsByCounty(CountyDto countyDto) {
//
//        return restaurantRepository.findAllByCounty(countyDto).stream().map(restaurantEntityToDtoConverter::convert)
//                .collect(Collectors.toList());
//    }

    public RestaurantDto updateRestaurant(UpdateRestaurantRequest request, long id) {

        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new RestaurantNotFoundException());
        Restaurant updatedFields = updateRestaurantRequestConverter.convert(request);

        restaurant.setName(updatedFields.getName());
        return restaurantEntityToDtoConverter.convert(restaurantRepository.save(restaurant));
    }

    public void deleteRestaurant(long id) {

        restaurantRepository.deleteById(id);
    }
}
