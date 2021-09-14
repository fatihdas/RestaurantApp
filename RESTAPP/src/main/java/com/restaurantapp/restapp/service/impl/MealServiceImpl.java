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
import com.restaurantapp.restapp.service.MealService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MealServiceImpl implements MealService {

    private final MealRepository mealRepository;
    private final BranchServiceImpl branchService;
    private final MealEntityToDtoConverter mealEntityToDtoConverter;
    private final CreateMealRequestConverter createMealRequestConverter;

    private final MenuServiceImpl menuService;
    private final UserServiceImpl userService;

    public MealServiceImpl(MealRepository mealRepository,
                           BranchServiceImpl branchService,
                           MealEntityToDtoConverter mealEntityToDtoConverter,
                           CreateMealRequestConverter createMealRequestConverter,
                           MenuServiceImpl menuService, UserServiceImpl userService) {
        this.mealRepository = mealRepository;
        this.branchService = branchService;
        this.mealEntityToDtoConverter = mealEntityToDtoConverter;
        this.createMealRequestConverter = createMealRequestConverter;
        this.menuService = menuService;
        this.userService = userService;
    }

    @Transactional
    public MealDto createMeal(CreateMealRequest request) {

        long ownerId = mealRepository.getOwnerIdByMenuId(request.getMenuId());
        if (!(userService.isOwner(ownerId))) {
            throw new InvalidOwnerException("invalid request!");
        }

        Meal meal = createMealRequestConverter.convert(request);
        mealRepository.save(meal);
        return mealEntityToDtoConverter.convert(meal);
    }

    public List<MealDto> getAllMeals() {

        List<Meal> mealList = mealRepository.findAll();
        return mealList.stream().map(mealEntityToDtoConverter::convert).collect(Collectors.toList());
    }

    public MealDto getMeal(long id) {

        Meal meal = mealRepository.findById(id).orElseThrow(() -> new MealNotFoundException(id));
        return mealEntityToDtoConverter.convert(meal);
    }

    @Override
    public List<MealDto> getMealByBranchId(long branchId) {

        Branch branch = branchService.getBranchByid(branchId);
        long menuId = branch.getMenu().getId();
        Menu menu = menuService.getMenu(menuId);
        return menu.getMealList().stream().map(mealEntityToDtoConverter::convert)
                .collect(Collectors.toList());
    }

    @Transactional
    public String updateMeal(UpdateMealRequest request, long id) {

        Meal meal = mealRepository.findById(id).orElseThrow(MealNotFoundException::new);

        meal.setName(request.getName());
        meal.setPrice(request.getPrice());
        return "Meal has been updated! id:" + id;
    }

    public void deleteMeal(long id) {

        mealRepository.deleteById(id);
    }

}
