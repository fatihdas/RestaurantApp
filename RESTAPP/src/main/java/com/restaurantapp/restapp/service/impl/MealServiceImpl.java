package com.restaurantapp.restapp.service.impl;

import com.restaurantapp.restapp.exception.MealNotFoundException;
import com.restaurantapp.restapp.model.converter.create.request.CreateMealRequestConverter;
import com.restaurantapp.restapp.model.converter.entity.todto.MealEntityToDtoConverter;
import com.restaurantapp.restapp.model.dto.MealDto;
import com.restaurantapp.restapp.model.dto.UserDto;
import com.restaurantapp.restapp.model.entity.Branch;
import com.restaurantapp.restapp.model.entity.Meal;
import com.restaurantapp.restapp.model.entity.Menu;
import com.restaurantapp.restapp.model.request.create.CreateMealRequest;
import com.restaurantapp.restapp.model.request.update.UpdateMealRequest;
import com.restaurantapp.restapp.repository.MealRepository;
import com.restaurantapp.restapp.service.MealService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MealServiceImpl implements MealService {

    private final MealRepository mealRepository;
    private final BranchServiceImpl branchService;
    private final MealEntityToDtoConverter mealEntityToDtoConverter;
    private final CreateMealRequestConverter createMealRequestConverter;
    private final TokenServiceImpl tokenService;

    private final MenuServiceImpl menuService;

    public MealServiceImpl(MealRepository mealRepository,
                           BranchServiceImpl branchService,
                           MealEntityToDtoConverter mealEntityToDtoConverter,
                           CreateMealRequestConverter createMealRequestConverter,
                           TokenServiceImpl tokenService, MenuServiceImpl menuService) {
        this.mealRepository = mealRepository;
        this.branchService = branchService;
        this.mealEntityToDtoConverter = mealEntityToDtoConverter;
        this.createMealRequestConverter = createMealRequestConverter;
        this.tokenService = tokenService;
        this.menuService = menuService;
    }

    public MealDto createMeal(CreateMealRequest request, HttpServletRequest httpServletRequest) throws Exception {

        UserDto userDto = tokenService.getUserByToken(httpServletRequest);
        if (!(tokenService.isOwnerBranch(userDto.getId(), httpServletRequest))) {
            throw new Exception("invalid request!");
        }

        return mealEntityToDtoConverter.convert(mealRepository.save(createMealRequestConverter.convert(request)));
    }

    public List<MealDto> getAllMeals() {

        return mealRepository.findAll().stream().map(mealEntityToDtoConverter::convert).collect(Collectors.toList());
    }

    public MealDto getMeal(long id) {

        return mealEntityToDtoConverter.convert(mealRepository.findById(id).orElseThrow(() -> new MealNotFoundException(id)));
    }

    @Override
    public List<MealDto> getMealByBranchId(long branchId) {

        long menuId = branchService.getBranchDto(branchId).getMenuId();
        Menu menu = menuService.getMenu(menuId);
        List<MealDto> mealDtos = menu.getMealList().stream().map(mealEntityToDtoConverter::convert)
                .collect(Collectors.toList());
        return mealDtos;
    }

    public String updateMeal(UpdateMealRequest request, long id) {

        Meal meal = mealRepository.findById(id).orElseThrow(() -> new MealNotFoundException());

        meal.setName(request.getName());
        meal.setPrice(request.getPrice());
        return "Meal has been updated! id:" + id;
    }

    public void deleteMeal(long id) {

        mealRepository.deleteById(id);
    }

    @Override
    public MealDto createMeal(CreateMealRequest request, long branchId) {
        Branch branch = branchService.getBranchByid(branchId);
        List<Meal> mealList = branch.getMenu().getMealList();
        mealList.add(createMealRequestConverter.convert(request));
        Menu menu = branch.getMenu();
        menu.setMealList(mealList);
        branch.setMenu(menu);

        return mealEntityToDtoConverter.convert(createMealRequestConverter.convert(request));

    }
}
