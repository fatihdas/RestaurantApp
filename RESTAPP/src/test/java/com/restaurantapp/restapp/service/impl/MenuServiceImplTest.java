package com.restaurantapp.restapp.service.impl;

import com.restaurantapp.restapp.exception.MenuNotFoundException;
import com.restaurantapp.restapp.model.converter.create.request.CreateMenuRequestConverter;
import com.restaurantapp.restapp.model.converter.entity.todto.MenuEntityToDtoConverter;
import com.restaurantapp.restapp.model.dto.MenuDto;
import com.restaurantapp.restapp.model.entity.Branch;
import com.restaurantapp.restapp.model.entity.Menu;
import com.restaurantapp.restapp.model.request.create.CreateMenuRequest;
import com.restaurantapp.restapp.model.request.update.UpdateMenuRequest;
import com.restaurantapp.restapp.repository.MenuRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class MenuServiceImplTest {

    private static final long ID = 1L;
    private static final String NAME = "doyuran menu";
    private static final long BRANCH_ID = 2L;
    private static final String BRANCH_NAME = "etiler sube";
    @Mock
    private MenuRepository menuRepository;

    @Mock
    private MenuEntityToDtoConverter menuEntityToDtoConverter;

    @Mock
    private CreateMenuRequestConverter createMenuRequestConverter;

    @Mock
    private BranchServiceImpl branchService;

    @InjectMocks
    private MenuServiceImpl menuService;

    @Test
    public void whenCreateMenuCalledByCreateMenuRequest_thenReturnSavedMenuDto() {
        CreateMenuRequest request = CreateMenuRequest.builder()
                .id(ID)
                .name(NAME)
                .build();
        Menu menu = Menu.builder()
                .name(NAME)
                .id(ID)
                .build();
        MenuDto menuDto = MenuDto.builder()
                .name(NAME)
                .id(ID)
                .build();
        Mockito.when(createMenuRequestConverter.convert(request)).thenReturn(menu);
        Mockito.when(menuRepository.save(menu)).thenReturn(menu);
        Mockito.when(menuEntityToDtoConverter.convert(menu)).thenReturn(menuDto);

        MenuDto result = menuService.createMenu(request);
        Assertions.assertEquals(ID, result.getId());
        Assertions.assertEquals(NAME, result.getName());

    }

    @Test
    public void whenGetMenuDtoCalledByBranchId_thenReturnMenuDto() {
        Branch branch = Branch.builder()
                .id(BRANCH_ID)
                .name(BRANCH_NAME)
                .menu(Menu.builder()
                        .id(ID)
                        .name(NAME)
                        .build())
                .build();
        MenuDto menuDto = MenuDto.builder()
                .id(ID)
                .name(NAME)
                .build();
        Mockito.when(branchService.getBranchByid(ID)).thenReturn(branch);
        Mockito.when(menuEntityToDtoConverter.convert(branch.getMenu())).thenReturn(menuDto);

        MenuDto result = menuService.getMenuDto(ID);
        Assertions.assertEquals(ID, result.getId());
        Assertions.assertEquals(NAME, result.getName());
    }

    @Test
    public void whenGetMenuCalledByValidMenuId_thenReturnMenu() {
        Menu menu = Menu.builder()
                .name(NAME)
                .id(ID)
                .build();
        Mockito.when(menuRepository.findById(ID)).thenReturn(java.util.Optional.ofNullable(menu));
        Menu result = menuService.getMenu(ID);
        Assertions.assertEquals(ID, result.getId());
        Assertions.assertEquals(NAME, result.getName());
    }

    @Test(expected = MenuNotFoundException.class)
    public void whenGetMenuCalledByInValidMenuId_thenThrowMenuNotFoundException() {
        Mockito.when(menuRepository.findById(ID)).thenReturn(Optional.empty());
        menuService.getMenu(ID);
    }

    @Test
    public void whenUpdateMenuCalledByValidMenuId_UpdateMenuRequest_thenReturnSuccessMessage() {
        UpdateMenuRequest request = UpdateMenuRequest.builder().build();
        Menu menu = Menu.builder()
                .id(ID)
                .name(NAME)
                .build();
        Mockito.when(menuRepository.findById(ID)).thenReturn(Optional.ofNullable(menu));
        String expectedMessage = "Menu has been updated! id:" + ID;
        String result = menuService.updateMenu(request, ID);
        Assertions.assertEquals(expectedMessage, result);
    }

    @Test(expected = MenuNotFoundException.class)
    public void whenUpdateMenuCalledByInValidMenuId_thenThrowMenuNotFoundException() {
        Mockito.when(menuRepository.findById(ID)).thenReturn(Optional.empty());
        menuService.updateMenu(new UpdateMenuRequest(), ID);
    }

    @Test
    public void whenDeleteMenuCalled_thenDeleteMenu() {
        menuService.deleteMenu(ID);
        Mockito.verify(menuRepository).deleteById(ID);

    }
}