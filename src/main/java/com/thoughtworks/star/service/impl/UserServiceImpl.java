package com.thoughtworks.star.service.impl;

import com.thoughtworks.star.configuration.security.JWTUser;
import com.thoughtworks.star.dto.ItemAdditionDTO;
import com.thoughtworks.star.entity.*;
import com.thoughtworks.star.repository.ItemRepository;
import com.thoughtworks.star.repository.UserRepository;
import com.thoughtworks.star.service.UserService;
import com.thoughtworks.star.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public User create(User user) {
        user.setId(StringUtils.randomUUID());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public List<ShoppingCartItem> findShoppingCartItems() {
        return authorizedUser().getShoppingCartItems();
    }

    @Override
    public void addShoppingCartItem(ItemAdditionDTO itemAdditionDTO) {
        User user = authorizedUser();
        user.getShoppingCartItems().add(ShoppingCartItem.builder().id(StringUtils.randomUUID())
                .item(itemRepository.findOne(itemAdditionDTO.getItemId()))
                .quantity(itemAdditionDTO.getQuantity()).build());
        userRepository.save(user);
    }

    @Override
    @Transactional
    public Address addAddress(Address address) {
        address.setId(StringUtils.randomUUID());
        User user = authorizedUser();
        user.getAddresses().add(address);
        userRepository.save(user);
        return address;
    }

    @Override
    public User findByName(String username) {
        return userRepository.findByName(username);
    }

    @Override
    public User findPrincipal() {
        return userRepository.findByName((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByName(username);
        if (user == null) {
            throw new UsernameNotFoundException("Username does not exist.");
        }
        Role role = user.getRole();
        return JWTUser.builder()
                .username(user.getName())
                .password(user.getPassword())
                .role(user.getRole().getSymbol().name())
                .privileges(role.getPrivileges().stream().map(Privilege::getSymbol).collect(toList()))
                .build();
    }

    private User authorizedUser() {
        JWTUser principal = (JWTUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findByName(principal.getUsername());
    }
}
