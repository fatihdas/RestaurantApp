package com.restaurantapp.restapp.service;

import com.restaurantapp.restapp.model.dto.MenuDto;
import com.restaurantapp.restapp.model.entity.Menu;
import com.restaurantapp.restapp.model.request.create.CreateMenuRequest;
import com.restaurantapp.restapp.model.request.update.UpdateMenuRequest;

import java.util.List;

public interface MenuService {

    MenuDto createMenu(CreateMenuRequest request);

    MenuDto getMenuDto(long id);

    Menu getMenu(long id);

    String updateMenu(UpdateMenuRequest request, long id);

    void deleteMenu(long id);
}
