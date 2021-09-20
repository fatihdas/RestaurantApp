package com.restaurantapp.restapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class InvalidOwnerException extends RuntimeException{
    public InvalidOwnerException(String message) {
        super(message);
    }

    public InvalidOwnerException() {
    }
}
