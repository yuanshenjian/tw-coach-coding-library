package com.thoughtworks.star.api;

import com.thoughtworks.star.configuration.security.APISecureRolePrivilege;
import com.thoughtworks.star.entity.User;
import com.thoughtworks.star.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    // TODO students implement
    @Autowired
    private UserService userService;

    // TODO students implement
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Secured(APISecureRolePrivilege.CREATE_USER)
    public User create(@RequestBody User user) {
        User createdUser = userService.create(user);
        createdUser.setPassword(null);
        return createdUser;
    }

    // TODO students implement
    @GetMapping("/{username}")
    @ResponseStatus(HttpStatus.CREATED)
    public User find(@PathVariable String username) {
        return userService.findByName(username);
    }

}
