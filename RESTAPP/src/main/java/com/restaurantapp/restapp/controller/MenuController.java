package com.restaurantapp.restapp.controller;

import com.restaurantapp.restapp.entity.Meal;
import com.restaurantapp.restapp.entity.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class MenuController {

    @Autowired
    private MenuService menuService;

    @PostMapping
    public ResponseEntity<Menu> add(@RequestBody Menu menu){

        return new ResponseEntity(menuService.save(item), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Menu>> getAll(){

        return new ResponseEntity(menuService.getAll(),HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Menu> getById(@PathVariable long id){

        return new ResponseEntity(menuService.getById(id),HttpStatus.OK)
    }

    @PutMapping
    public ResponseEntity<Menu> update(@RequestBody Menu menu){

        return new ResponseEntity(menuService.update(meal),HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Menu> delete(@PathVariable long id){
        return new ResponseEntity(menuService.delete(id),HttpStatus.OK);
    }
}
