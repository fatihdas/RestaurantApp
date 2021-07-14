package com.restaurantapp.restapp.service;

import com.restaurantapp.restapp.model.Menu;
import com.restaurantapp.restapp.model.User;
import com.restaurantapp.restapp.repository.MenuRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MenuService {

    private final MenuRepository menuRepository;

    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public Menu save(Menu menu){

        return menuRepository.save(menu);
    }

    public List<Menu> getAll(){

        return menuRepository.findAll();
    }

    public Menu getById(long id){

        return menuRepository.findById(id).orElse(null);
    }

    public Menu update(Menu menu, long id){

        Menu menu1 = menuRepository.findById(id).orElse(null);

        menu1.setId(menu.getId());
        menu1.setMealList(menu.getMealList());
        menu1.setBranch(menu.getBranch());

        menuRepository.save(menu1);

        return menu1;
    }

    public Menu delete(long id){

        menuRepository.deleteById(id);

        return menuRepository.findById(id).orElse(null);
    }
}
