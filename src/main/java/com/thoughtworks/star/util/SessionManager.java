package com.thoughtworks.star.util;

import com.thoughtworks.star.entity.User;
import com.thoughtworks.star.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SessionManager {
    private static User currentUser;

    @Autowired
    private UserRepository userRepository;

    public void setCurrentUser(User user) {
        currentUser = user;
    }

    public User getCurrentUser() {
        if (currentUser == null) {
            currentUser = userRepository.findAll().get(0);
        }
        return currentUser;
    }
}
