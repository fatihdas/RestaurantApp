package com.restaurantapp.restapp.service;

import com.restaurantapp.restapp.model.Basket;
import com.restaurantapp.restapp.model.Branch;
import com.restaurantapp.restapp.model.User;
import com.restaurantapp.restapp.repository.BasketRepository;
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
public class BasketServiceTest {

    @Mock
    private BasketRepository basketRepository;

    @InjectMocks
    private BasketService basketService;

    @Test
    public void save() {

        Basket basket = this.generateBasket();

        Mockito.when(basketRepository.save(Mockito.any(Basket.class))).thenReturn(basket);

        Basket createAddress = basketService.save(new Basket());

        Assertions.assertEquals(basket, createAddress);
    }

    @Test
    public void getAll() {

        List<Basket> basketList = new ArrayList<>();
        basketList.add(this.generateBasket());

        Mockito.when(basketRepository.findAll()).thenReturn(basketList);

        List<Basket> createBasketList = basketService.getAll();

        Assertions.assertEquals(basketList, createBasketList);
    }

    @Test
    public void getById() {

        Basket basket = this.generateBasket();

        Mockito.when(basketRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(basket));

        Basket createBasket = basketService.getById(2);

        Assertions.assertEquals(basket, createBasket);
    }

    @Test
    public void update() {

        Basket basket = this.generateBasket();

        Mockito.when(basketRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(basket));

        Mockito.when(basketRepository.save(Mockito.any(Basket.class))).thenReturn(basket);

        Basket createBasket = basketService.update(new Basket());

        Assertions.assertEquals(basket, createBasket);
    }

    @Test
    public void delete() {

        basketRepository.deleteById(2L);
        Mockito.verify(basketRepository).deleteById(2L);

        String deleteBranch = basketService.delete(3);

        Assertions.assertEquals("SUCCESS", deleteBranch);
    }

    private Basket generateBasket() {

        return Basket.builder()
                .user(User.builder().name("testname").build())
                .count(4)
                .totalPrice(15.25f)
                .build();

    }

}