package com.restaurantapp.restapp.controller;

import com.restaurantapp.restapp.model.dto.MenuDto;
import com.restaurantapp.restapp.model.request.create.CreateMenuRequest;
import com.restaurantapp.restapp.service.impl.MenuServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RunWith(MockitoJUnitRunner.class)
public class MenuControllerTest {

    private static final String NAME = "Kral ikili";
    private static final long BRANCH_ID = 1L;
    @Mock
    private MenuServiceImpl menuService;

    @InjectMocks
    private MenuController menuController;

    @Test
    public void whenCreateMenuCalledByCreateMenuRequest_thenReturnSavedMenu() {
        MenuDto menuDto = this.generateMenu();
        CreateMenuRequest request = CreateMenuRequest.builder()
                .name(NAME)
                .build();
        Mockito.when(menuService.createMenu(request)).thenReturn(menuDto);
        ResponseEntity<MenuDto> responseEntity = menuController.createMenu(request);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertEquals(NAME, responseEntity.getBody().getName());
    }

    @Test
    public void whenGetMenuByBranchIdCalledByBranchId_thenReturnMenu() {
        MenuDto menuDto = this.generateMenu();
        Mockito.when(menuService.getMenuDto(BRANCH_ID)).thenReturn(menuDto);
        ResponseEntity<MenuDto> responseEntity = menuController.getMenuByBranchId(BRANCH_ID);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertEquals(NAME, responseEntity.getBody().getName());
    }

    private MenuDto generateMenu() {
        return MenuDto.builder()
                .name(NAME)
                .build();
    }
}