package com.restaurantapp.restapp.service;

import com.restaurantapp.restapp.model.Meal;
import com.restaurantapp.restapp.repository.MealRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class MealServiceTest {

    @Mock
    private MealRepository mealRepository;

    @InjectMocks
    private MealService mealService;

    @Test
    public void save() {

        Meal meal = this.generateMeal();

        Mockito.when(mealRepository.save(Mockito.any(Meal.class))).thenReturn(meal);

        Meal createMeal = mealService.save(new Meal());

        Assertions.assertEquals(meal, createMeal);
    }

    @Test
    public void getAll() {

        List<Meal> mealList = new ArrayList<>();
        mealList.add(this.generateMeal());

        Mockito.when(mealRepository.findAll()).thenReturn(mealList);

        List<Meal> createMealList = mealService.getAll();

        Assertions.assertEquals(mealList, createMealList);
    }

    @Test
    public void getById() {

        Meal meal = this.generateMeal();

        Mockito.when(mealRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(meal));

        Meal createMeal = mealService.getById(2);

        Assertions.assertEquals(meal, createMeal);
    }

    @Test
    public void update() {

        Meal meal = this.generateMeal();

        Mockito.when(mealRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(meal));

        Mockito.when(mealRepository.save(Mockito.any(Meal.class))).thenReturn(meal);

        Meal createMeal = mealService.update(new Meal());

        Assertions.assertEquals(meal, createMeal);
    }

    @Test
    public void delete() {

        mealRepository.deleteById(2L);
        Mockito.verify(mealRepository).deleteById(2L);

        String deleteMeal = mealService.delete(3);

        Assertions.assertEquals("SUCCESS", deleteMeal);
    }

    private Meal generateMeal() {
        return Meal.builder()
                .name("Kumpir + Kola")
                .price(40)
                .build();
    }

}