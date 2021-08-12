package com.restaurantapp.restapp.service;

import com.restaurantapp.restapp.model.dto.ItemDto;
import com.restaurantapp.restapp.model.request.create.CreateItemRequest;
import com.restaurantapp.restapp.model.request.update.UpdateItemRequest;

import java.util.List;

public interface ItemService {

    ItemDto createItem(CreateItemRequest request);

    List<ItemDto> getAllItems();

    ItemDto getItem(long id);

    ItemDto updateItem(UpdateItemRequest request, long id);

    void deleteItem(long id);
}
