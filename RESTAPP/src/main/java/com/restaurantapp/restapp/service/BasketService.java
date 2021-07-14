package com.restaurantapp.restapp.service;

import com.restaurantapp.restapp.model.Basket;
import com.restaurantapp.restapp.repository.BasketRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BasketService {

    private final BasketRepository basketRepository;

    public BasketService(BasketRepository basketRepository) {
        this.basketRepository = basketRepository;
    }

    public Basket save(Basket basket){

        return basketRepository.save(basket);
    }

    public List<Basket> getAll(){

        return basketRepository.findAll();
    }

    public Basket getById(long id){

        return basketRepository.findById(id).orElse(null);
    }

    public Basket update(Basket basket, long id){

        Basket basket1 = basketRepository.findById(id).orElse(null);

        basket1.setId(basket.getId());
        basket1.setCount(basket.getCount());
        basket1.setMealList(basket.getMealList());
        basket1.setUser(basket.getUser());
        basket1.setTotalPrice(basket.getTotalPrice());

        basketRepository.save(basket1);

        return basket1;


    }

    public Basket delete(long id){

        basketRepository.deleteById(id);

        return basketRepository.findById(id).orElse(null);
    }
}
