package com.thoughtworks.star.service.impl;

import com.thoughtworks.star.entity.Item;
import com.thoughtworks.star.repository.ItemRepository;
import com.thoughtworks.star.service.ItemService;
import com.thoughtworks.star.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    // TODO students implement
    @Override
    @Transactional
    public Item create(Item item) {
        item.setId(StringUtils.randomUUID());
        return itemRepository.save(item);
    }

    // TODO students implement
    @Override
    public List<Item> findAll() {
        return itemRepository.findAll();
    }
}
