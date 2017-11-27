package com.thoughtworks.star.service;

import com.thoughtworks.star.entity.Order;

import java.util.List;

public interface OrderService {
    Order create(List<String> itemIds);

    List<Order> findCurrentUserOrder();
}
