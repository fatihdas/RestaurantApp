package com.restaurantapp.restapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MealNotFoundException extends RuntimeException{

    public MealNotFoundException(long id) {
        super("Meal not found id:" + id);
    }
}
