package com.restaurantapp.restapp.service;

import com.restaurantapp.restapp.exception.BasketNotFoundException;
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

    public Basket save(Basket basket) {

        return basketRepository.save(basket);
    }

    public List<Basket> getAll() {

        return basketRepository.findAll();
    }

    public Basket getById(long id) {

        return basketRepository.findById(id).orElseThrow(() -> new BasketNotFoundException(id));
    }

    public Basket update(Basket basket) {

        basketRepository.findById(basket.getId()).orElseThrow(() -> new BasketNotFoundException(basket.getId()));
        return basketRepository.save(basket);
    }

    public String delete(long id) {

        basketRepository.deleteById(id);
        return "SUCCESS";
    }
}
