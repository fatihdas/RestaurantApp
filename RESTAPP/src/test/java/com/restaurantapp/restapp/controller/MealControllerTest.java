package com.restaurantapp.restapp.controller;

import com.restaurantapp.restapp.model.dto.MealDto;
import com.restaurantapp.restapp.model.request.create.CreateMealRequest;
import com.restaurantapp.restapp.service.impl.MealServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class MealControllerTest {

    private static final String NAME = "Köfte";
    private static final float PRICE = 22f;
    private static final long BRANCH_ID = 1L;
    @Mock
    private MealServiceImpl mealService;

    @InjectMocks
    private MealController mealController;

    @Test
    public void whenCreateMealCalledByCreateMealRequest_thenReturnSavedMeal() {
        MealDto mealDto = this.generateMeal();
        CreateMealRequest request = CreateMealRequest.builder()
                .name(NAME)
                .price(PRICE)
                .build();
        Mockito.when(mealService.createMeal(request)).thenReturn(mealDto);
        ResponseEntity<MealDto> responseEntity = mealController.createMeal(request);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertEquals(NAME, responseEntity.getBody().getName());
        Assertions.assertEquals(PRICE, responseEntity.getBody().getPrice());

    }

    @Test
    public void whenGetMealByBranchIdCalledByBranchId_thenReturnMealList() {
        List<MealDto> mealDtoList = Arrays.asList(this.generateMeal());
        Mockito.when(mealService.getMealByBranchId(BRANCH_ID)).thenReturn(mealDtoList);
        ResponseEntity<List<MealDto>> responseEntity = mealController.getMealByBranchId(BRANCH_ID);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertEquals(NAME, responseEntity.getBody().get(0).getName());
        Assertions.assertEquals(PRICE, responseEntity.getBody().get(0).getPrice());
    }

    private MealDto generateMeal() {
        return MealDto.builder()
                .name(NAME)
                .price(PRICE)
                .build();
    }
}