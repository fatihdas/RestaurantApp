package com.restaurantapp.restapp.service;

import com.restaurantapp.restapp.model.dto.MealDto;
import com.restaurantapp.restapp.model.entity.Meal;
import com.restaurantapp.restapp.model.request.create.CreateMealRequest;
import com.restaurantapp.restapp.model.request.update.UpdateMealRequest;
import com.restaurantapp.restapp.repository.MealRepository;
import com.restaurantapp.restapp.service.impl.MealServiceImpl;
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
public class MealServiceImplTest {

    @Mock
    private MealRepository mealRepository;

    @InjectMocks
    private MealServiceImpl mealServiceImpl;

    @Test
    public void save() {

        Meal meal = this.generateMeal();

        Mockito.when(mealRepository.save(Mockito.any(Meal.class))).thenReturn(meal);

        MealDto createMeal = mealServiceImpl.createMeal(new CreateMealRequest());

        Assertions.assertEquals(meal, createMeal);
    }

    @Test
    public void getAll() {

        List<Meal> mealList = new ArrayList<>();
        mealList.add(this.generateMeal());

        Mockito.when(mealRepository.findAll()).thenReturn(mealList);

        List<MealDto> createMealList = mealServiceImpl.getAllMeals();

        Assertions.assertEquals(mealList, createMealList);
    }

    @Test
    public void getById() {

        Meal meal = this.generateMeal();

        Mockito.when(mealRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(meal));

        MealDto createMeal = mealServiceImpl.getMeal(2);

        Assertions.assertEquals(meal, createMeal);
    }

    @Test
    public void update() {

        Meal meal = this.generateMeal();

        Mockito.when(mealRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(meal));

        Mockito.when(mealRepository.save(Mockito.any(Meal.class))).thenReturn(meal);

        String createMeal = mealServiceImpl.updateMeal(new UpdateMealRequest(),8);

        Assertions.assertEquals(meal, createMeal);
    }

    @Test
    public void delete() {

        mealRepository.deleteById(2L);
        Mockito.verify(mealServiceImpl).deleteMeal(2L);
    }

    private Meal generateMeal() {
        return Meal.builder()
                .name("Kumpir + Kola")
                .price(40)
                .build();
    }

}