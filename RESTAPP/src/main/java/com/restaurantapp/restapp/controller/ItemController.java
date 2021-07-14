package com.restaurantapp.restapp.controller;

import com.restaurantapp.restapp.entity.County;
import com.restaurantapp.restapp.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping
    public ResponseEntity<Item> add(@RequestBody Item item){

        return new ResponseEntity(itemService.save(item), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Item>> getAll(){

        return new ResponseEntity(itemService.getAll(),HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Item> getById(@PathVariable long id){

        return new ResponseEntity(itemService.getById(id),HttpStatus.OK)
    }

    @PutMapping
    public ResponseEntity<Item> update(@RequestBody Item item){

        return new ResponseEntity(itemService.update(item),HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Item> delete(@PathVariable long id){
        return new ResponseEntity(itemService.delete(id),HttpStatus.OK);
    }
}
