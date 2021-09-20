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
import com.restaurantapp.restapp.service.MenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;
    private final MenuEntityToDtoConverter menuEntityToDtoConverter;
    private final CreateMenuRequestConverter createMenuRequestConverter;
    private final BranchServiceImpl branchService;

    public MenuServiceImpl(MenuRepository menuRepository,
                           MenuEntityToDtoConverter menuEntityToDtoConverter,
                           CreateMenuRequestConverter createMenuRequestConverter,
                           BranchServiceImpl branchService) {
        this.menuRepository = menuRepository;
        this.menuEntityToDtoConverter = menuEntityToDtoConverter;
        this.createMenuRequestConverter = createMenuRequestConverter;
        this.branchService = branchService;
    }

    @Transactional
    public MenuDto createMenu(CreateMenuRequest request) {

        Menu menu = menuRepository.save(createMenuRequestConverter.convert(request));
        return menuEntityToDtoConverter.convert(menu);
    }

    public MenuDto getMenuDto(long branchId) {

        Branch branch = branchService.getBranchByid(branchId);
        return menuEntityToDtoConverter.convert(branch.getMenu());
    }

    @Override
    public Menu getMenu(long id) {
        return menuRepository.findById(id).orElseThrow(() -> new MenuNotFoundException());
    }

    public String updateMenu(UpdateMenuRequest request, long id) {

        Menu menu = menuRepository.findById(id).orElseThrow(() -> new MenuNotFoundException());

        menu.setName(request.getName());

        return "Menu has been updated! id:" + id;
    }

    public void deleteMenu(long id) {

        menuRepository.deleteById(id);
    }
}
