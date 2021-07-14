package com.restaurantapp.restapp.controller;

import com.restaurantapp.restapp.entity.Comment;
import com.restaurantapp.restapp.entity.County;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class CountyController {

    @Autowired
    private CountyService countyService;

    @PostMapping
    public ResponseEntity<County> add(@RequestBody County county){

        return new ResponseEntity(commentService.save(county), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<County>> getAll(){

        return new ResponseEntity(commentService.getAll(),HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<County> getById(@PathVariable long id){

        return new ResponseEntity(commentService.getById(id),HttpStatus.OK)
    }

    @PutMapping
    public ResponseEntity<County> update(@RequestBody County county){

        return new ResponseEntity(commentService.update(county),HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<County> delete(@PathVariable long id){
        return new ResponseEntity(commentService.delete(id),HttpStatus.OK);
    }
}
