package com.restaurantapp.restapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CityNotFoundException extends RuntimeException{

    public CityNotFoundException(long id) {
        super("City not found id:" + id);
    }

    public CityNotFoundException() {

    }
}
