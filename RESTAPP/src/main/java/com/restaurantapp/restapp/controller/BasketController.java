package com.restaurantapp.restapp.controller;

import com.restaurantapp.restapp.entity.Basket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Access;
import java.io.Serializable;
import java.util.List;

@RestController("basket")
public class BasketController {

    @Autowired
    private BasketService basketService;

    @PostMapping
    public ResponseEntity<Basket> add(@RequestBody Basket basket){

        return new ResponseEntity(basketService.save(basket), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Basket>> getAll(){

        return new ResponseEntity(basketService.getAll(),HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Basket> getById(@PathVariable long id){

        return new ResponseEntity(basketService.getById(id),HttpStatus.OK)
    }

    @PutMapping
    public ResponseEntity<Basket> update(@RequestBody Basket basket){

        return new ResponseEntity(basketService.update(basket),HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Basket> delete(@PathVariable long id){
        return new ResponseEntity(basketService.delete(id),HttpStatus.OK);
    }
}
