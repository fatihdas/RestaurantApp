package com.restaurantapp.restapp.service;

import com.restaurantapp.restapp.model.Branch;
import com.restaurantapp.restapp.model.Menu;
import com.restaurantapp.restapp.repository.MenuRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class MenuServiceTest {

    @Mock
    private MenuRepository menuRepository;

    @InjectMocks
    private MenuService menuService;

    @Test
    public void save() {

        Menu menu = this.generateMenu();

        Mockito.when(menuRepository.save(Mockito.any(Menu.class))).thenReturn(menu);

        Menu createMenu = menuService.save(new Menu());

        Assertions.assertEquals(menu, createMenu);
    }

    @Test
    public void getAll() {

        List<Menu> menuList = new ArrayList<>();
        menuList.add(this.generateMenu());

        Mockito.when(menuRepository.findAll()).thenReturn(menuList);

        List<Menu> createMenuList = menuService.getAll();

        Assertions.assertEquals(menuList, createMenuList);
    }

    @Test
    public void getById() {

        Menu menu = this.generateMenu();

        Mockito.when(menuRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(menu));

        Menu createMenu = menuService.getById(2);

        Assertions.assertEquals(menu, createMenu);
    }

    @Test
    public void update() {

        Menu menu = this.generateMenu();

        Mockito.when(menuRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(menu));

        Mockito.when(menuRepository.save(Mockito.any(Menu.class))).thenReturn(menu);

        Menu createMenu = menuService.update(new Menu());

        Assertions.assertEquals(menu, createMenu);
    }

    @Test
    public void delete() {

        menuRepository.deleteById(2L);
        Mockito.verify(menuRepository).deleteById(2L);

        String deleteMenu = menuService.delete(3);

        Assertions.assertEquals("SUCCESS", deleteMenu);
    }

    private Menu generateMenu() {
        return Menu.builder()
                .branch(Branch.builder().name("etilerşubesi").build())
                .build();
    }

}