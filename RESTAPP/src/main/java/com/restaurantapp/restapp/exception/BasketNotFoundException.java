package com.restaurantapp.restapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BasketNotFoundException extends RuntimeException {

    public BasketNotFoundException(long id) {
        super("Basket not found id:" + id);
    }
}