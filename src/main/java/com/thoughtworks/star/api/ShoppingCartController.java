package com.thoughtworks.star.api;

import com.thoughtworks.star.dto.ItemAdditionDTO;
import com.thoughtworks.star.entity.ShoppingCartItem;
import com.thoughtworks.star.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shopping-cart-items")
public class ShoppingCartController {

    // TODO students implement
    @Autowired
    private UserService userService;

    // TODO students implement
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ShoppingCartItem> list() {
        return userService.findShoppingCartItems();
    }

    // TODO students implement
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addItem(@RequestBody ItemAdditionDTO itemAdditionDTO) {
        userService.addShoppingCartItem(itemAdditionDTO);
    }

}
