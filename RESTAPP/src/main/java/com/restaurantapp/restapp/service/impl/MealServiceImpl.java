package com.restaurantapp.restapp.service.impl;

import com.restaurantapp.restapp.exception.MealNotFoundException;
import com.restaurantapp.restapp.model.converter.create.request.CreateMealRequestConverter;
import com.restaurantapp.restapp.model.converter.entity.todto.MealEntityToDtoConverter;
import com.restaurantapp.restapp.model.dto.MealDto;
import com.restaurantapp.restapp.model.dto.UserDto;
import com.restaurantapp.restapp.model.entity.Meal;
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
    private final MealEntityToDtoConverter mealEntityToDtoConverter;
    private final CreateMealRequestConverter createMealRequestConverter;
    private final HttpServletRequest httpServletRequest;
    private final TokenServiceImpl tokenService;

    public MealServiceImpl(MealRepository mealRepository,
                           MealEntityToDtoConverter mealEntityToDtoConverter,
                           CreateMealRequestConverter createMealRequestConverter,
                           HttpServletRequest httpServletRequest, TokenServiceImpl tokenService) {
        this.mealRepository = mealRepository;
        this.mealEntityToDtoConverter = mealEntityToDtoConverter;
        this.createMealRequestConverter = createMealRequestConverter;
        this.httpServletRequest = httpServletRequest;
        this.tokenService = tokenService;
    }

    public MealDto createMeal(CreateMealRequest request,HttpServletRequest httpServletRequest) throws Exception {

        UserDto userDto = tokenService.getUserByToken(httpServletRequest);
        if (!(tokenService.isOwnerBranch(userDto.getId(),httpServletRequest ))){
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

    public String updateMeal(UpdateMealRequest request, long id) {

        Meal meal = mealRepository.findById(id).orElseThrow(() -> new MealNotFoundException());

        meal.setName(request.getName());
        meal.setPrice(request.getPrice());
        return "Meal has been updated! id:" + id;
    }

    public void deleteMeal(long id) {

        mealRepository.deleteById(id);
    }
}
