package com.restaurantapp.restapp.controller;

import com.restaurantapp.restapp.entity.City;
import com.restaurantapp.restapp.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping
    public ResponseEntity<Comment> add(@RequestBody Comment comment){

        return new ResponseEntity(commentService.save(comment), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Comment>> getAll(){

        return new ResponseEntity(commentService.getAll(),HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Comment> getById(@PathVariable long id){

        return new ResponseEntity(commentService.getById(id),HttpStatus.OK)
    }

    @PutMapping
    public ResponseEntity<Comment> update(@RequestBody Comment comment){

        return new ResponseEntity(commentService.update(comment),HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Comment> delete(@PathVariable long id){
        return new ResponseEntity(commentService.delete(id),HttpStatus.OK);
    }
}
