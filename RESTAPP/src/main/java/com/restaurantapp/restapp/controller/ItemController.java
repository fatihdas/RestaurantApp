package com.restaurantapp.restapp.controller;

import com.restaurantapp.restapp.model.Item;
import com.restaurantapp.restapp.service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

<<<<<<< HEAD
@RestController
@RequestMapping("item")
=======
@RestController("item")
>>>>>>> 09.08
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    public ResponseEntity<Item> add(@RequestBody Item item) {

        return new ResponseEntity<>(itemService.save(item), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Item>> getAll() {

        return new ResponseEntity<>(itemService.getAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Item> getById(@PathVariable long id) {

        return new ResponseEntity<>(itemService.getById(id), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Item> update(@RequestBody Item item) {

        return new ResponseEntity<>(itemService.update(item), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {

        return new ResponseEntity(itemService.delete(id), HttpStatus.OK);
    }
}
