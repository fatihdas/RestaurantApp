package com.restaurantapp.restapp.service;

import com.restaurantapp.restapp.exception.ItemNotFoundException;
import com.restaurantapp.restapp.model.Item;
import com.restaurantapp.restapp.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Item save(Item item) {

        return itemRepository.save(item);
    }

    public List<Item> getAll() {

        return itemRepository.findAll();
    }

    public Item getById(long id) {

        return itemRepository.findById(id).orElseThrow(() -> new ItemNotFoundException(id));
    }

    public Item update(Item item) {

        itemRepository.findById(item.getId()).orElseThrow(() -> new ItemNotFoundException(item.getId()));
        return itemRepository.save(item);
    }

    public Item delete(long id) {

        itemRepository.deleteById(id);

        return itemRepository.findById(id).orElseThrow(() -> new ItemNotFoundException(id));
    }
}
