package com.restaurantapp.restapp.controller;

import com.restaurantapp.restapp.model.Menu;
import com.restaurantapp.restapp.service.MenuService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

<<<<<<< HEAD
@RestController
@RequestMapping("menu")
=======
@RestController("menu")
>>>>>>> 09.08
public class MenuController {

    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @PostMapping
    public ResponseEntity<Menu> add(@RequestBody Menu menu) {

        return new ResponseEntity<>(menuService.save(menu), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Menu>> getAll() {

        return new ResponseEntity<>(menuService.getAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Menu> getById(@PathVariable long id) {

        return new ResponseEntity<>(menuService.getById(id), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Menu> update(@RequestBody Menu menu) {

        return new ResponseEntity<>(menuService.update(menu), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {

        return new ResponseEntity(menuService.delete(id), HttpStatus.OK);
    }
}
