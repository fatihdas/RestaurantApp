package com.restaurantapp.restapp.service;

import com.restaurantapp.restapp.model.Item;
import com.restaurantapp.restapp.repository.ItemRepository;
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
public class ItemServiceTest {

    @Mock
    private ItemRepository itemRepository;

    @InjectMocks
    private ItemService itemService;

    @Test
    public void save() {

        Item item = this.generateItem();

        Mockito.when(itemRepository.save(Mockito.any(Item.class))).thenReturn(item);

        Item createItem = itemService.save(new Item());

        Assertions.assertEquals(item, createItem);
    }

    @Test
    public void getAll() {

        List<Item> itemList = new ArrayList<>();
        itemList.add(this.generateItem());

        Mockito.when(itemRepository.findAll()).thenReturn(itemList);

        List<Item> createItemList = itemService.getAll();

        Assertions.assertEquals(itemList, createItemList);
    }

    @Test
    public void getById() {

        Item item = this.generateItem();

        Mockito.when(itemRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(item));

        Item createItem = itemService.getById(2);

        Assertions.assertEquals(item, createItem);
    }

    @Test
    public void update() {

        Item item = this.generateItem();

        Mockito.when(itemRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(item));

        Mockito.when(itemRepository.save(Mockito.any(Item.class))).thenReturn(item);

        Item createItem = itemService.update(new Item());

        Assertions.assertEquals(item, createItem);
    }

    @Test
    public void delete() {

        itemRepository.deleteById(2L);
        Mockito.verify(itemRepository).deleteById(2L);

        String deleteItem = itemService.delete(3);

        Assertions.assertEquals("SUCCESS", deleteItem);
    }

    private Item generateItem() {
        return Item.builder()
                .name("Patlıcak Musakka")
                .price(25.50f)
                .build();
    }

}