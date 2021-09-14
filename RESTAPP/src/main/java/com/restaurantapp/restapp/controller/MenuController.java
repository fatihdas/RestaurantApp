package com.restaurantapp.restapp.controller;

import com.restaurantapp.restapp.model.dto.MenuDto;
import com.restaurantapp.restapp.model.request.create.CreateMenuRequest;
import com.restaurantapp.restapp.service.impl.MenuServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("menu")
public class MenuController {

    private final MenuServiceImpl menuService;

    public MenuController(MenuServiceImpl menuService) {
        this.menuService = menuService;
    }

    @PostMapping
    public ResponseEntity<MenuDto> createMenu(@RequestBody CreateMenuRequest request){
        return new ResponseEntity<>(menuService.createMenu(request),HttpStatus.OK);
    }

    @GetMapping("/{branchId}")
    public ResponseEntity<MenuDto> getMenuByBranchId(@PathVariable long branchId) {
        return new ResponseEntity<>(menuService.getMenuDto(branchId), HttpStatus.OK);
    }
}
