package com.restaurantapp.restapp.service;

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

    public Meal save(Meal meal){

        return mealRepository.save(meal);
    }

    public List<Meal> getAll(){

        return mealRepository.findAll();
    }

    public Meal getById(long id){

        return mealRepository.findById(id).orElse(null);
    }

    public Meal update(Meal meal, long id){

        Meal meal1 = mealRepository.findById(id).orElse(null);

        meal1.setId(meal.getId());
        meal1.setName(meal.getName());
        meal1.setItemList(meal.getItemList());
        meal1.setPrice(meal.getPrice());

        mealRepository.save(meal1);

        return meal1;
    }

    public Meal delete(long id){

        mealRepository.deleteById(id);

        return mealRepository.findById(id).orElse(null);
    }
}
