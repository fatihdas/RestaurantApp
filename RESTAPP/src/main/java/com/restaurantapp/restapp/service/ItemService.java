package com.restaurantapp.restapp.service;

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

    public Item save(Item item){

        return itemRepository.save(item);
    }

    public List<Item> getAll(){

        return itemRepository.findAll();
    }

    public Item getById(long id){

        return itemRepository.findById(id).orElse(null);
    }

    public Item update(Item item, long id){

        Item item1 = itemRepository.findById(id).orElse(null);

        item1.setId(item.getId());
        item1.setName(item.getName());
        item1.setPrice(item.getPrice());

        itemRepository.save(item1);

        return item1;
    }

    public Item delete(long id){

        itemRepository.deleteById(id);

        return itemRepository.findById(id).orElse(null);
    }
}
