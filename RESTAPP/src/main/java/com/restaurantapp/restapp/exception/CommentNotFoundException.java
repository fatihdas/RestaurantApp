package com.restaurantapp.restapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CommentNotFoundException extends RuntimeException{

    public CommentNotFoundException(long id) {
        super("Comment not found id:" + id);
    }
}