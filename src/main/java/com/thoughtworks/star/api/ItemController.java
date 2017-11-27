package com.thoughtworks.star.api;

import com.thoughtworks.star.entity.Item;
import com.thoughtworks.star.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    // TODO students implement
    @Autowired
    private ItemService itemService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Item item) {
        itemService.create(item);
    }

    // TODO students implement
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Item> list() {
        return itemService.findAll();
    }

}
