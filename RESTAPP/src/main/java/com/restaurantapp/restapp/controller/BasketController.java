package com.restaurantapp.restapp.controller;

import com.restaurantapp.restapp.model.Basket;
import com.restaurantapp.restapp.service.BasketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("basket")
public class BasketController {

    private final BasketService basketService;

    public BasketController(BasketService basketService) {
        this.basketService = basketService;
    }

    @PostMapping
    public ResponseEntity<Basket> addBasket(@RequestBody Basket basket){

        return new ResponseEntity(basketService.save(basket), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Basket>> getAllBasket(){

        return new ResponseEntity(basketService.getAll(),HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Basket> getBasketById(@PathVariable long id){

        return new ResponseEntity(basketService.getById(id),HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Basket> updateBasket(@RequestBody Basket basket, @PathVariable long id){

        return new ResponseEntity(basketService.update(basket, id),HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Basket> deleteBasket(@PathVariable long id){
        return new ResponseEntity(basketService.delete(id),HttpStatus.OK);
    }
}
