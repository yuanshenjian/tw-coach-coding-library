package com.thoughtworks.star.api;

import com.thoughtworks.star.entity.Address;
import com.thoughtworks.star.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/addresses")
public class AddressController {

    // TODO students implement
    @Autowired
    private UserService userService;

    // TODO students implement
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Address create(@RequestBody Address address) {
        return userService.addAddress(address);
    }

}
