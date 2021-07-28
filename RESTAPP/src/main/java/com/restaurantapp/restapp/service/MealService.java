package com.restaurantapp.restapp.service;

import com.restaurantapp.restapp.exception.MealNotFoundException;
import com.restaurantapp.restapp.model.Meal;
import com.restaurantapp.restapp.repository.MealRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MealService {

    private final MealRepository mealRepository;

    public MealService(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }

    public Meal save(Meal meal) {

        return mealRepository.save(meal);
    }

    public List<Meal> getAll() {

        return mealRepository.findAll();
    }

    public Meal getById(long id) {

        return mealRepository.findById(id).orElseThrow(() -> new MealNotFoundException(id));
    }

    public Meal update(Meal meal) {

        mealRepository.findById(meal.getId()).orElseThrow(() -> new MealNotFoundException(meal.getId()));
        return mealRepository.save(meal);
    }

    public Meal delete(long id) {

        mealRepository.deleteById(id);

        return mealRepository.findById(id).orElseThrow(() -> new MealNotFoundException(id));
    }
}
