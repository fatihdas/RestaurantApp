package com.restaurantapp.restapp.controller;

import com.restaurantapp.restapp.model.dto.MenuDto;
import com.restaurantapp.restapp.service.impl.MenuServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("menu")
public class MenuController {

    private final MenuServiceImpl menuService;

    public MenuController(MenuServiceImpl menuService) {
        this.menuService = menuService;
    }

    @GetMapping("getMenu/{branchId}")
    public ResponseEntity<MenuDto> getMenuByBranchId(@PathVariable long branchId) {
        return new ResponseEntity<>(menuService.getMenu(branchId), HttpStatus.OK);
    }
}
