package com.restaurantapp.restapp.service.impl;

import com.restaurantapp.restapp.exception.MealNotFoundException;
import com.restaurantapp.restapp.model.converter.create.request.toentity.CreateMealRequestConverter;
import com.restaurantapp.restapp.model.converter.entity.todto.MealEntityToDtoConverter;
import com.restaurantapp.restapp.model.converter.update.toentity.UpdateMealRequestConverter;
import com.restaurantapp.restapp.model.dto.MealDto;
import com.restaurantapp.restapp.model.entity.Meal;
import com.restaurantapp.restapp.model.request.create.CreateMealRequest;
import com.restaurantapp.restapp.model.request.update.UpdateMealRequest;
import com.restaurantapp.restapp.repository.MealRepository;
import com.restaurantapp.restapp.service.MealService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MealServiceImpl implements MealService {

    private final MealRepository mealRepository;
    private final MealEntityToDtoConverter mealEntityToDtoConverter;
    private final CreateMealRequestConverter createMealRequestConverter;
    private final UpdateMealRequestConverter updateMealRequestConverter;

    public MealServiceImpl(MealRepository mealRepository, MealEntityToDtoConverter mealEntityToDtoConverter, CreateMealRequestConverter createMealRequestConverter, UpdateMealRequestConverter updateMealRequestConverter) {
        this.mealRepository = mealRepository;
        this.mealEntityToDtoConverter = mealEntityToDtoConverter;
        this.createMealRequestConverter = createMealRequestConverter;
        this.updateMealRequestConverter = updateMealRequestConverter;
    }

    public MealDto createMeal(CreateMealRequest request) {

        return mealEntityToDtoConverter.convert(mealRepository.save(createMealRequestConverter.convert(request)));
    }

    public List<MealDto> getAllMeals() {

        return mealRepository.findAll().stream().map(mealEntityToDtoConverter::convert).collect(Collectors.toList());
    }

    public MealDto getMeal(long id) {

        return mealEntityToDtoConverter.convert(mealRepository.findById(id).orElseThrow(() -> new MealNotFoundException(id)));
    }

    public MealDto updateMeal(UpdateMealRequest request, long id) {

        Meal meal = mealRepository.findById(id).orElseThrow(() -> new MealNotFoundException());
        Meal updatedFields = updateMealRequestConverter.convert(request);

        meal.setName(updatedFields.getName());
        meal.setPrice(updatedFields.getPrice());
        return mealEntityToDtoConverter.convert(mealRepository.save(meal));
    }

    public void deleteMeal(long id) {

        mealRepository.deleteById(id);
    }
}
