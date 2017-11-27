package com.thoughtworks.star.service;

import com.thoughtworks.star.entity.Item;

import java.util.List;

public interface ItemService {
    Item create(Item item);

    List<Item> findAll();
}
