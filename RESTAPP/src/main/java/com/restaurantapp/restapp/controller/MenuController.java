package com.restaurantapp.restapp.controller;

import com.restaurantapp.restapp.model.dto.MenuDto;
import com.restaurantapp.restapp.model.request.create.CreateMenuRequest;
import com.restaurantapp.restapp.model.request.update.UpdateMenuRequest;
import com.restaurantapp.restapp.service.impl.MenuServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("menu")
public class MenuController {

    private final MenuServiceImpl menuServiceImpl;

    public MenuController(MenuServiceImpl menuServiceImpl) {
        this.menuServiceImpl = menuServiceImpl;
    }

    @PostMapping
    public ResponseEntity<MenuDto> createMenu(@RequestBody CreateMenuRequest request) {

        return new ResponseEntity<>(menuServiceImpl.createMenu(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<MenuDto>> getAllMenus() {

        return new ResponseEntity<>(menuServiceImpl.getAllMenu(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<MenuDto> getMenu(@PathVariable long id) {

        return new ResponseEntity<>(menuServiceImpl.getMenu(id), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<MenuDto> update(@RequestBody UpdateMenuRequest request, @PathVariable long id) {

        return new ResponseEntity<>(menuServiceImpl.updateMenu(request, id), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {

        menuServiceImpl.deleteMenu(id);
        return ResponseEntity.ok().build();
    }
}
