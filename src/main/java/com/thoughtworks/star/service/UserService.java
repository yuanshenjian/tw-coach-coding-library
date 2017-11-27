package com.thoughtworks.star.service;

import com.thoughtworks.star.dto.ItemAdditionDTO;
import com.thoughtworks.star.entity.Address;
import com.thoughtworks.star.entity.ShoppingCartItem;
import com.thoughtworks.star.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    User create(User user);

    List<ShoppingCartItem> findShoppingCartItems();

    void addShoppingCartItem(ItemAdditionDTO itemAdditionDTO);

    Address addAddress(Address address);

    User findByName(String username);

    User findPrincipal();
}
