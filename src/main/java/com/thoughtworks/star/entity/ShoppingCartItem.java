package com.thoughtworks.star.entity;

import lombok.*;

import javax.persistence.*;

// TODO students implement
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_shopping_cart_item")
public class ShoppingCartItem {

    @Id
    private String id;

    // TODO students implement
    @ManyToOne(cascade = CascadeType.ALL)
    private Item item;

    private long quantity;

}
