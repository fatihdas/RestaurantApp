package com.restaurantapp.restapp.service.impl;

import com.restaurantapp.restapp.exception.InvalidOwnerException;
import com.restaurantapp.restapp.exception.MealNotFoundException;
import com.restaurantapp.restapp.model.converter.create.request.CreateMealRequestConverter;
import com.restaurantapp.restapp.model.converter.entity.todto.MealEntityToDtoConverter;
import com.restaurantapp.restapp.model.dto.MealDto;
import com.restaurantapp.restapp.model.entity.Branch;
import com.restaurantapp.restapp.model.entity.Meal;
import com.restaurantapp.restapp.model.entity.Menu;
import com.restaurantapp.restapp.model.request.create.CreateMealRequest;
import com.restaurantapp.restapp.model.request.update.UpdateMealRequest;
import com.restaurantapp.restapp.repository.MealRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class MealServiceImplTest {

    private static final long OWNER_ID = 1l;
    private static final long ID = 1l;
    private static final long MENU_ID = 1l;
    private static final String NAME = "Köfte";
    private static final long BRANCH_ID = 1l;
    private static final String UPDATE_NAME = "update name";
    @Mock
    private MealRepository mealRepository;

    @Mock
    private BranchServiceImpl branchService;

    @Mock
    private MealEntityToDtoConverter mealEntityToDtoConverter;

    @Mock
    private CreateMealRequestConverter createMealRequestConverter;

    @Mock
    private MenuServiceImpl menuService;

    @Mock
    private UserServiceImpl userService;

    @InjectMocks
    private MealServiceImpl mealService;

    @Test
    public void whenCreateMealCalled_thenReturnMealDto() {
        CreateMealRequest request = CreateMealRequest.builder()
                .id(ID)
                .menuId(MENU_ID)
                .name(NAME)
                .build();
        Meal meal = Meal.builder()
                .id(ID)
                .name(NAME)
                .build();
        MealDto mealDto = MealDto.builder()
                .id(ID)
                .name(NAME)
                .build();
        Mockito.when(mealRepository.getOwnerIdByMenuId(MENU_ID)).thenReturn(OWNER_ID);
        Mockito.when(userService.isOwner(OWNER_ID)).thenReturn(true);
        Mockito.when(createMealRequestConverter.convert(request)).thenReturn(meal);
        Mockito.when(mealEntityToDtoConverter.convert(meal)).thenReturn(mealDto);

        MealDto result = mealService.createMeal(request);
        Assertions.assertEquals(ID, result.getId());
        Assertions.assertEquals(NAME, result.getName());
    }

    @Test(expected = InvalidOwnerException.class)
    public void whenCreateMealCalled_isNotOwner_thenThrowInvalidOwnerException() {
        CreateMealRequest request = CreateMealRequest.builder()
                .id(ID)
                .menuId(MENU_ID)
                .name(NAME)
                .build();
        Mockito.when(mealRepository.getOwnerIdByMenuId(MENU_ID)).thenReturn(OWNER_ID);
        Mockito.when(userService.isOwner(OWNER_ID)).thenReturn(false);
        mealService.createMeal(request);
    }

    @Test
    public void whenGetAllMealsCalled_thenReturnMealDtoList() {
        List<Meal> mealList = Collections.singletonList(Meal.builder()
                .id(ID)
                .name(NAME)
                .build());
        MealDto mealDto = MealDto.builder()
                .name(NAME)
                .id(ID)
                .build();
        Mockito.when(mealRepository.findAll()).thenReturn(mealList);
        Mockito.when(mealEntityToDtoConverter.convert(Mockito.any(Meal.class))).thenReturn(mealDto);

        List<MealDto> result = mealService.getAllMeals();
        Assertions.assertEquals(ID, result.get(0).getId());
        Assertions.assertEquals(NAME, result.get(0).getName());
    }

    @Test
    public void whenGetMealCalledByValidMealId_thenReturnMealDto() {
        Meal meal = Meal.builder()
                .name(NAME)
                .id(ID)
                .build();
        MealDto mealDto = MealDto.builder()
                .id(ID)
                .name(NAME)
                .build();
        Mockito.when(mealRepository.findById(ID)).thenReturn(java.util.Optional.ofNullable(meal));
        Mockito.when(mealEntityToDtoConverter.convert(meal)).thenReturn(mealDto);

        MealDto result = mealService.getMeal(ID);
        Assertions.assertEquals(ID, result.getId());
        Assertions.assertEquals(NAME, result.getName());
    }

    @Test(expected = MealNotFoundException.class)
    public void whenGetMealCalledByInValidMealId_thenThrowMealNotFoundException() {
        Mockito.when(mealRepository.findById(ID)).thenReturn(Optional.empty());
        mealService.getMeal(ID);
    }

    @Test
    public void whenGetMealByBranchIdCalledValidBranchId_thenReturnMealDtoListOfBranch() {
        Menu menu = Menu.builder()
                .id(MENU_ID)
                .mealList(Collections.singletonList(Meal.builder()
                        .id(ID)
                        .name(NAME)
                        .build())).build();
        MealDto mealDto = MealDto.builder()
                .name(NAME)
                .id(ID)
                .build();
        Branch branch = Branch.builder().menu(menu).build();
        Mockito.when(branchService.getBranchByid(BRANCH_ID)).thenReturn(branch);
        Mockito.when(menuService.getMenu(MENU_ID)).thenReturn(menu);
        Mockito.when(mealEntityToDtoConverter.convert(Mockito.any(Meal.class))).thenReturn(mealDto);

        List<MealDto> result = mealService.getMealByBranchId(BRANCH_ID);
        Assertions.assertEquals(ID, result.get(0).getId());
        Assertions.assertEquals(NAME, result.get(0).getName());
    }

    @Test
    public void whenUpdateMealCalledByValidMealId_ValidRequest_thenReturnSuccessMessage() {
        UpdateMealRequest request = UpdateMealRequest.builder()
                .name(UPDATE_NAME)
                .build();
        Meal meal = Meal.builder()
                .name(NAME)
                .id(ID)
                .build();
        Mockito.when(mealRepository.findById(ID)).thenReturn(Optional.ofNullable(meal));
        mealService.updateMeal(request, ID);
        Mockito.verify(mealRepository).findById(ID);

    }

    @Test(expected = MealNotFoundException.class)
    public void whenUpdateMealCalledByInValidMealId_thenThrowMealNotFoundException() {
        UpdateMealRequest request = UpdateMealRequest.builder()
                .name(UPDATE_NAME)
                .build();
        Meal meal = Meal.builder()
                .name(NAME)
                .id(ID)
                .build();
        Mockito.when(mealRepository.findById(ID)).thenReturn(Optional.empty());
        mealService.updateMeal(request, ID);
    }

    @Test
    public void whenDeleteMealCalledByMealId_thenDeleteMeal() {
        mealService.deleteMeal(ID);
        Mockito.verify(mealRepository).deleteById(ID);
    }
}