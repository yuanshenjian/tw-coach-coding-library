package com.thoughtworks.star.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

// TODO students implement
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_user")
public class User {
    @Id
    private String id;

    private String name;
    private String password;

    private String defaultAddress;

    private int age;

    // TODO students implement
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<Address> addresses;

    // TODO students implement
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<ShoppingCartItem> shoppingCartItems;

    // TODO students implement
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "role")
    private Role role;
}
