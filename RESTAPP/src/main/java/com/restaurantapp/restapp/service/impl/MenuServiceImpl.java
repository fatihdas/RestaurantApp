package com.restaurantapp.restapp.service.impl;

import com.restaurantapp.restapp.exception.MenuNotFoundException;
import com.restaurantapp.restapp.model.converter.create.request.toentity.CreateMenuRequestConverter;
import com.restaurantapp.restapp.model.converter.update.toentity.UpdateMenuRequestConverter;
import com.restaurantapp.restapp.model.dto.MenuDto;
import com.restaurantapp.restapp.model.converter.entity.todto.MenuEntityToDtoConverter;
import com.restaurantapp.restapp.model.entity.Menu;
import com.restaurantapp.restapp.model.request.create.CreateMenuRequest;
import com.restaurantapp.restapp.model.request.update.UpdateMenuRequest;
import com.restaurantapp.restapp.repository.MenuRepository;
import com.restaurantapp.restapp.service.MenuService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;
    private final MenuEntityToDtoConverter menuEntityToDtoConverter;
    private final CreateMenuRequestConverter createMenuRequestConverter;
    private final UpdateMenuRequestConverter updateMenuRequestConverter;

    public MenuServiceImpl(MenuRepository menuRepository, MenuEntityToDtoConverter menuEntityToDtoConverter, CreateMenuRequestConverter createMenuRequestConverter, UpdateMenuRequestConverter updateMenuRequestConverter) {
        this.menuRepository = menuRepository;
        this.menuEntityToDtoConverter = menuEntityToDtoConverter;
        this.createMenuRequestConverter = createMenuRequestConverter;
        this.updateMenuRequestConverter = updateMenuRequestConverter;
    }

    public MenuDto createMenu(CreateMenuRequest request) {

        return menuEntityToDtoConverter.convert(menuRepository.save(createMenuRequestConverter.convert(request)));
    }

    public List<MenuDto> getAllMenu() {

        return menuRepository.findAll().stream().map(menuEntityToDtoConverter::convert).collect(Collectors.toList());
    }

    public MenuDto getMenu(long id) {

        return menuEntityToDtoConverter.convert(menuRepository.findById(id)
                .orElseThrow(() -> new MenuNotFoundException(id)));
    }

    public MenuDto updateMenu(UpdateMenuRequest request, long id) {

        Menu menu = menuRepository.findById(id).orElseThrow(() -> new MenuNotFoundException());
        Menu updatedFields = updateMenuRequestConverter.convert(request);

        menu.setName(updatedFields.getName());

        return menuEntityToDtoConverter.convert(menuRepository.save(menu));
    }

    public void deleteMenu(long id) {

        menuRepository.deleteById(id);
    }
}
