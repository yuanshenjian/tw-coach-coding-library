package com.thoughtworks.star;

import com.thoughtworks.star.entity.User;

import java.util.Collection;

public interface UserCache {
    User findById(String id);

    User findByName(String username);

    User save(User user);

    void clear();

    Collection<User> findAll();

    User update(User user);

    Collection<User> findByAge(int age);
}
