package com.thoughtworks.star.repository;

import com.thoughtworks.star.entity.Order;
import com.thoughtworks.star.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, String> {
    List<Order> findByUserId(String username);
}
