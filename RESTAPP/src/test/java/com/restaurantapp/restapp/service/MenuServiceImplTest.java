//package com.restaurantapp.restapp.service;
//
//import com.restaurantapp.restapp.model.dto.MenuDto;
//import com.restaurantapp.restapp.model.entity.Branch;
//import com.restaurantapp.restapp.model.entity.Menu;
//import com.restaurantapp.restapp.model.request.create.CreateMenuRequest;
//import com.restaurantapp.restapp.model.request.update.UpdateMenuRequest;
//import com.restaurantapp.restapp.repository.MenuRepository;
//import com.restaurantapp.restapp.service.impl.MenuServiceImpl;
//import org.junit.Test;
//import org.junit.jupiter.api.Assertions;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.MockitoJUnitRunner;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@RunWith(MockitoJUnitRunner.class)
//public class MenuServiceImplTest {
//
//    @Mock
//    private MenuRepository menuRepository;
//
//    @InjectMocks
//    private MenuServiceImpl menuServiceImpl;
//
//    @Test
//    public void save() {
//
//        Menu menu = this.generateMenu();
//
//        Mockito.when(menuRepository.save(Mockito.any(Menu.class))).thenReturn(menu);
//
//        MenuDto createMenu = menuServiceImpl.createMenu(new CreateMenuRequest());
//
//        Assertions.assertEquals(menu, createMenu);
//    }
//
//    @Test
//    public void getById() {
//
//        Menu menu = this.generateMenu();
//
//        Mockito.when(menuRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(menu));
//
//        MenuDto createMenu = menuServiceImpl.getMenuDto(2);
//
//        Assertions.assertEquals(menu, createMenu);
//    }
//
//    @Test
//    public void update() {
//
//        Menu menu = this.generateMenu();
//
//        Mockito.when(menuRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(menu));
//
//        Mockito.when(menuRepository.save(Mockito.any(Menu.class))).thenReturn(menu);
//
//        String createMenu = menuServiceImpl.updateMenu(new UpdateMenuRequest(),6);
//
//        Assertions.assertEquals(menu, createMenu);
//    }
//
//    @Test
//    public void delete() {
//
//        menuRepository.deleteById(2L);
//        Mockito.verify(menuServiceImpl).deleteMenu(2L);
//    }
//
//    private Menu generateMenu() {
//        return Menu.builder()
//                .build();
//    }
//
//}