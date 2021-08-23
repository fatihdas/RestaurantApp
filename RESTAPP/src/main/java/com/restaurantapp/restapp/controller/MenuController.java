package com.restaurantapp.restapp.controller;

import com.restaurantapp.restapp.model.dto.MenuDto;
import com.restaurantapp.restapp.model.request.create.CreateMealRequest;
import com.restaurantapp.restapp.model.request.create.CreateMenuRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("menu")
public class MenuController {

    @PostMapping
    public ResponseEntity<MenuDto> createMenu(@RequestBody CreateMealRequest request, @PathVariable long menuId){

        return ResponseEntity.ok(null);
    }
}
